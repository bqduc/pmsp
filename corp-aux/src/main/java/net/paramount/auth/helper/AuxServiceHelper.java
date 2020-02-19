/**
 * 
 */
package net.paramount.auth.helper;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import net.paramount.auth.domain.UserProfile;
import net.paramount.auth.entity.UserAccount;
import net.paramount.auth.service.UserAccountService;
import net.paramount.common.CommonUtility;

/**
 * @author ducbq
 *
 */
@Component
public class AuxServiceHelper {
	@Inject 
	private UserAccountService userAuthenticationService;

	public UserProfile getUserAccountProfile(final String userAccountSsoId) {
		if (CommonUtility.isEmpty(userAccountSsoId))
			return null;

		UserAccount userAccount = userAuthenticationService.get(userAccountSsoId);
		if (null==userAccount)
			return null;

		return UserProfile.builder()
				.userAccount(userAccount)
				.build();
	}
}
