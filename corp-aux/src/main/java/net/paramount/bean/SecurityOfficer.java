/**
 * 
 */
package net.paramount.bean;

import java.util.Calendar;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import net.paramount.auth.domain.UserProfile;
import net.paramount.auth.service.AuthorizationService;
import net.paramount.common.ListUtility;
import net.paramount.framework.component.CompCore;

/**
 * @author ducbq
 *
 */
@Component("securityOfficer")
@SessionScope
public class SecurityOfficer extends CompCore {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6067936455848876100L;

	@Inject
	private AuthorizationService authorizationService;

	private Map<String, GrantedAuthority> permissionMap = ListUtility.createMap();

	public boolean hasPermission(String target, String actions) {
		System.out.println(Calendar.getInstance().getTime() + "\t. Target, action: " + target + "$" + actions);
		return Boolean.TRUE;
	}

	/*
		public boolean hasPermission(String name, String action, Object... arguments) {
			// TODO: arguments values on the right for further control must be controlled ...
			String key = name + ":" + action;
			boolean hasPermimssion = permissionMap.containsKey(key);
			// log.info("User [#0] has permission [#1] on action [#2]", activeUser.getUserSsoId(), hasPermimssion, key);
			// return hasPermimssion;
			return true;
		}
	*/
	public Boolean hasPermission(String target) {
		UserProfile userProfile = authorizationService.getUserProfile();
		System.out.println(Calendar.getInstance().getTime() + "\t. Target: " + target + ". User: " + userProfile.getDisplayName());
		return true;
		// return permissionMap.containsKey(target);
	}

	public void initializeSessionData() {
		UserProfile userProfile = authorizationService.getUserProfile();
		if (false==userProfile.isPresentUserAccount()) {
			System.out.println("Current user is anonymous. ");
			return;
		}

		System.out.println("Initializing session data of user: " + userProfile.getDisplayName());
	}
}
