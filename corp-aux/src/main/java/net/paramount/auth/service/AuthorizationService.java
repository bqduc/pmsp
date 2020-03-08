/**
 * 
 */
package net.paramount.auth.service;

import net.paramount.auth.domain.UserProfile;
import net.paramount.auth.entity.UserAccount;
import net.paramount.auth.exception.CorporateAuthenticationException;
import net.paramount.exceptions.CorporateAuthException;
import net.paramount.exceptions.ObjectNotFoundException;
import net.paramount.framework.model.ExecutionContext;

/**
 * @author ducbq
 *
 */
public interface AuthorizationService {
	UserProfile authenticate(String ssoId, String password) throws CorporateAuthenticationException;
	UserProfile authenticate(String token) throws CorporateAuthenticationException;

	UserProfile getUserProfile() throws CorporateAuthenticationException;
	
	boolean hasPermission(String target, String action) throws CorporateAuthenticationException;

	UserProfile register(ExecutionContext context) throws CorporateAuthException;

	UserAccount getUserAccount(String ssoId)  throws ObjectNotFoundException;

	UserAccount confirmByToken(String token)  throws ObjectNotFoundException;
}
