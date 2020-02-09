/**
 * 
 */
package net.paramount.auth.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.paramount.auth.domain.UserAccountProfile;
import net.paramount.auth.entity.UserAccount;
import net.paramount.auth.exception.UserAuthenticationException;

/**
 * @author ducbq
 *
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
	@Inject
	private UserAccountService userAccountService;

	@Override
	public UserAccountProfile authenticate(String ssoId, String password) throws UserAuthenticationException {
		UserAccount fetchedUserAccount = this.userAccountService.authenticate(ssoId, password);
		return getAuthenticatedUserProfile(fetchedUserAccount);
	}

	@Override
	public UserAccountProfile authenticate(String loginToken) throws UserAuthenticationException {
		UserAccount fetchedUserAccount = this.userAccountService.authenticate(loginToken);
		return getAuthenticatedUserProfile(fetchedUserAccount);
	}

	private UserAccountProfile getAuthenticatedUserProfile(UserAccount userAccount) {
		return UserAccountProfile.builder()
		.userAccount(userAccount)
		.build();
	}
}
