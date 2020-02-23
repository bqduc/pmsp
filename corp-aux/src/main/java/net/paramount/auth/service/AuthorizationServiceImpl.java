/**
 * 
 */
package net.paramount.auth.service;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import net.paramount.auth.domain.UserProfile;
import net.paramount.auth.entity.UserAccount;
import net.paramount.auth.exception.CorporateAuthenticationException;
import net.paramount.autx.SecurityServiceContextHelper;
import net.paramount.comm.comp.Communicator;
import net.paramount.comm.domain.CorpMimeMessage;
import net.paramount.comm.global.CommunicatorConstants;
import net.paramount.exceptions.CorporateAuthException;
import net.paramount.framework.model.ExecutionContext;

/**
 * @author ducbq
 *
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
	@Inject
	private Communicator emailCommunicator;

	@Inject
	private UserAccountService userAccountService;

	@Inject 
	private SecurityServiceContextHelper securityContextHolderServiceHelper;

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
		Authentication authentication = securityContextHolderServiceHelper.getAuthentication();
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

	@Override
	public UserProfile register(ExecutionContext context) throws CorporateAuthException {
		UserAccount updatedUserAccount = null;
		UserProfile registrationProfile = null;
		try {
			updatedUserAccount = (UserAccount)context.get(CommunicatorConstants.CTX_USER_ACCOUNT);
			updatedUserAccount = userAccountService.saveOrUpdate(updatedUserAccount);

			CorpMimeMessage corpMimeMessage = (CorpMimeMessage)context.get(CommunicatorConstants.CTX_MIME_MESSAGE);
			if (null==corpMimeMessage) {
				corpMimeMessage = CorpMimeMessage.builder()
						.subject(CommunicatorConstants.CTX_DEFAULT_REGISTRATION_SUBJECT)
						.recipients(new String[] {updatedUserAccount.getEmail()})
						.build();
			}
			corpMimeMessage.setRecipients(new String[] {updatedUserAccount.getEmail()});

			context.put(CommunicatorConstants.CTX_MIME_MESSAGE, corpMimeMessage);
			
			registrationProfile = UserProfile.builder()
					.displayName(updatedUserAccount.getDisplayName())
					.userAccount(updatedUserAccount)
					.build();

			emailCommunicator.send(context);
		} catch (Exception e) {
			throw new CorporateAuthException(e);
		}
		return registrationProfile;
	}	
}
