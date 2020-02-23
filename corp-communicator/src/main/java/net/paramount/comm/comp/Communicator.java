/**
 * 
 */
package net.paramount.comm.comp;

import net.paramount.comm.domain.CorpMimeMessage;
import net.paramount.exceptions.CommunicatorException;
import net.paramount.framework.model.ExecutionContext;

/**
 * @author ducbq
 *
 */
public interface Communicator {
	void sendEmail(CorpMimeMessage mailMessage) throws CommunicatorException;
	void send(ExecutionContext context) throws CommunicatorException;
}
