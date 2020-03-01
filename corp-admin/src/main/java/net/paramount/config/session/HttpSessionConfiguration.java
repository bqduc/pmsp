/**
 * 
 */
package net.paramount.config.session;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.util.SerializationUtils;

import net.paramount.autx.SecurityServiceContextHelper;
import net.paramount.common.ListUtility;
import net.paramount.framework.component.CompCore;

/**
 * @author ducbq
 *
 */
@Configuration
public class HttpSessionConfiguration extends CompCore {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2038636375337878759L;

	private static final int MAX_SESSION_SIZE = 1024 * 10; // 10 KB as the recommended size
	
	static final Set<HttpSession> activeSessions = Collections.synchronizedSet(new HashSet<HttpSession>());

	@Inject 
	private SecurityServiceContextHelper securityServiceContextHelper;
	public List<HttpSession> getActiveSessions() {
		return ListUtility.createDataList(activeSessions);
	}

	@Bean // bean for http session listener
	public HttpSessionListener httpSessionListener() {
		return new HttpSessionListener() {
			@Override
			public void sessionCreated(HttpSessionEvent event) { 
				// This method will be called when session created
				synchronized (activeSessions) {
					activeSessions.add(event.getSession());
				}
				
				log.info("The HttpSession {} was created on {}.", event.getSession().getId(), new Date());
				//System.out.println("Session Created with session id+" + event.getSession().getId());
			}

			@Override
			public void sessionDestroyed(HttpSessionEvent event) { // This method will be automatically called when session destroyed
				synchronized (activeSessions) {
					activeSessions.remove(event.getSession());
				}
				
				checkFinalSessionSizeForBloat(event);
					
				log.debug("The HttpSession {} was destroyed on {} for user '{}'.", new Object[] { event.getSession().getId(), new Date(), getUserAssociatedWithCurrentSession() });
				//System.out.println("Session Destroyed, Session id:" + se.getSession().getId());

			}
		};
	}

	/*
	 * This check is a simplistic aid in managing session bloat.  Keeping the
	 * total session small can significantly help with scalability as
	 * techniques like session persistence or replication are more feasible. 
	 */
	private void checkFinalSessionSizeForBloat(HttpSessionEvent event) {
		long approximateSessionSizeInBytes = getApproximateSessionSize(event.getSession());
			
		log.info("The HttpSession was approximately {} bytes when serialized.", approximateSessionSizeInBytes);
		
		if (approximateSessionSizeInBytes > MAX_SESSION_SIZE) {
			log.info("The HttpSession is too damn high!");
		}
	}

	/*
	 * This is probably not the best way to determine the actual session size
	 * however, it does roughly measure the serialized size which is typically
	 * what we are interested in from a performance perspective (i.e. from a
	 * simplistic session replication scenario).
	 * 
	 * TODO: Move to a session "helper" class
	 */
	private long getApproximateSessionSize(HttpSession currentSession) {
		long totalBytes = 0L;
		
		for (Enumeration<String> sessionAttribute = currentSession.getAttributeNames(); sessionAttribute.hasMoreElements(); ) {
			String sessionAttributeKey = sessionAttribute.nextElement();
			
			try {
				Serializable sessionAttributeValue = (Serializable) currentSession.getAttribute(sessionAttributeKey);
				totalBytes += SerializationUtils.serialize(sessionAttributeKey).length;
				totalBytes += SerializationUtils.serialize(sessionAttributeValue).length;
				
				// TODO this trace should go somewhere else
				log.info("Session attribute: '{}' = '{}'", sessionAttributeKey,sessionAttributeValue);
			}
			catch (ClassCastException ex) {
				// we should avoid having unserializable objects in the session
				log.info("Found unserializable object in the HttpSession at '{}' as type {}.", sessionAttributeKey, currentSession.getAttribute(sessionAttributeKey).getClass());
			}
		}
		
		return totalBytes;
	}
	
	/*
	 * 
	 */
	private String getUserAssociatedWithCurrentSession() {
		Authentication userAuthentication = securityServiceContextHelper.getAuthentication();
		return userAuthentication == null ? "*Anonymous*" : userAuthentication.getName();
	}
}
