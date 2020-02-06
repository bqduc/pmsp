/**
 * 
 */
package net.paramount.comm.component;

import org.springframework.stereotype.Component;

import net.paramount.comm.domain.MailMessage;
import net.paramount.exceptions.CommunicatorException;

/**
 * @author ducbq
 *
 */
@Component
class EmailServiceImpl implements CommunicationService {

	@Override
	public void send(MailMessage mailMessage) throws CommunicatorException {
		System.out.println("net.paramount.comm.component.EmailServiceImpl.send(MailMessage)");
	}
	
}
