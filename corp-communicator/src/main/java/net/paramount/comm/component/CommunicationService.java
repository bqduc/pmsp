/**
 * 
 */
package net.paramount.comm.component;

import net.paramount.comm.domain.MailMessage;
import net.paramount.exceptions.CommunicatorException;

/**
 * @author ducbq
 *
 */
public interface CommunicationService {
	void send(MailMessage mailMessage) throws CommunicatorException;
}
