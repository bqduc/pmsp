/**
 * 
 */
package net.paramount.comm.comp;

import javax.inject.Named;

import org.springframework.stereotype.Component;

import net.paramount.comm.domain.MailMessage;
import net.paramount.exceptions.CommunicatorException;

/**
 * @author ducbq
 *
 */
@Named
@Component
class EmailCommunicator implements Communicatior {

	@Override
	public void sendEmail(MailMessage mailMessage) throws CommunicatorException {
		System.out.println("net.paramount.comm.component.EmailServiceImpl.send(MailMessage)");
	}
	
}
