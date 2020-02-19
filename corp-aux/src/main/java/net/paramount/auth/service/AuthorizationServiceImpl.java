/**
 * 
 */
package net.paramount.auth.service;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import net.paramount.auth.domain.UserProfile;
import net.paramount.auth.entity.UserAccount;
import net.paramount.auth.exception.CorporateAuthenticationException;

/**
 * @author ducbq
 *
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
	@Inject
	private UserAccountService userAccountService;

	@Override
	public UserProfile authenticate(String ssoId, String password) throws CorporateAuthenticationException {
		UserAccount fetchedUserAccount = this.userAccountService.getUserAccount(ssoId, password);
		return getAuthenticatedUserProfile(fetchedUserAccount);
	}

	@Override
	public UserProfile authenticate(String loginToken) throws CorporateAuthenticationException {
		UserAccount fetchedUserAccount = this.userAccountService.getUserAccount(loginToken);
		return getAuthenticatedUserProfile(fetchedUserAccount);
	}

	private UserProfile getAuthenticatedUserProfile(UserAccount userAccount) {
		return UserProfile.builder()
		.userAccount(userAccount)
		.build();
	}

	@Override
	public UserProfile getUserProfile() throws CorporateAuthenticationException {
		UserProfile fetchedResponse = null;
		Object systemPrincipal = getSystemPrincipal();
		if (systemPrincipal instanceof User || systemPrincipal instanceof UserAccount) {
			UserAccount userAccount = this.userAccountService.get(((User)systemPrincipal).getUsername());
			fetchedResponse = UserProfile
			.builder()
			.userAccount(userAccount)
			.build();
		} else if (systemPrincipal instanceof String){ //Anonymous user - Not logged in
			fetchedResponse = UserProfile.builder()
			.displayName((String)systemPrincipal)
			.build();
		}
		return fetchedResponse;
	}

	private Object getSystemPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() instanceof String) {
			return authentication.getPrincipal();
		}

		return authentication.getPrincipal();
	}

	@Override
	public boolean hasPermission(String target, String action) throws CorporateAuthenticationException {
		// TODO Auto-generated method stub
		return false;
	}	
}
