/**
 * 
 */
package net.paramount.auth.exception;

import net.paramount.exceptions.CorpAuthenticationException;
import net.paramount.model.AuthenticationStage;

/**
 * @author bqduc
 *
 */
public class CorporateAuthenticationException extends CorpAuthenticationException /*AuthenticationException*/ {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2145491649369081533L;

	private AuthenticationStage errorId;

	public CorporateAuthenticationException(String msg) {
		super(msg);
	}

	public CorporateAuthenticationException(AuthenticationStage errorId, String msg) {
		super(msg);
		this.errorId = errorId;
	}

	public AuthenticationStage getErrorId() {
		return errorId;
	}

	public void setErrorId(AuthenticationStage errorId) {
		this.errorId = errorId;
	}
}
