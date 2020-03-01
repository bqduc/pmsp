/**
 * 
 */
package net.paramount.auth.exception;

import net.paramount.domain.model.AuthenticationStage;
import net.paramount.exceptions.CorporateAuthException;

/**
 * @author bqduc
 *
 */
public class CorporateAuthenticationException extends CorporateAuthException /*AuthenticationException*/ {
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
