/**
 * 
 */
package net.paramount.comm.comp;

import net.paramount.comm.domain.MailMessage;
import net.paramount.exceptions.CommunicatorException;

/**
 * @author ducbq
 *
 */
public interface Communicatior {
	void sendEmail(MailMessage mailMessage) throws CommunicatorException;
}
