/**
 * 
 */
package net.paramount.auth.service;

import net.paramount.auth.domain.UserAccountProfile;
import net.paramount.auth.exception.UserAuthenticationException;

/**
 * @author ducbq
 *
 */
public interface AuthorizationService {
	UserAccountProfile authenticate(String ssoId, String password) throws UserAuthenticationException;
	UserAccountProfile authenticate(String token) throws UserAuthenticationException;
}
