package net.paramount.auth.component;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import net.paramount.auth.domain.UserProfile;
import net.paramount.auth.exception.CorporateAuthenticationException;
import net.paramount.auth.service.AuthorizationService;
import net.paramount.common.CommonUtility;
import net.paramount.framework.component.CompCore;

/**
 * Created by aLeXcBa1990 on 24/11/2018.
 * 
 */

@Component(value="corpAuthenticationProvider")
public class CorpAuthenticationProvider extends CompCore implements AuthenticationProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8355652678792800184L;

	@Inject 
	private AuthorizationService authorizationService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authentication.getName().length() < 150) {
			return authenticateBySsoId(authentication.getName(), authentication.getCredentials().toString());
		}

		return authenticateByToken(authentication.getName());
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	private Authentication authenticateBySsoId(String ssoId, String password) throws AuthenticationException {
		CorporateAuthenticationException userAuthenticationException = null;
		UserProfile userAccountProfile = null;
		try {
			userAccountProfile = authorizationService.authenticate(ssoId, password);
		} catch (AuthenticationException uae) {
			userAuthenticationException = (CorporateAuthenticationException) uae;
		}

		if (null != userAccountProfile)
			return new UsernamePasswordAuthenticationToken(ssoId, password, userAccountProfile.getUserAccount().getAuthorities());			

		throw userAuthenticationException;
	}

	private Authentication authenticateByToken(String token) throws AuthenticationException {
		CorporateAuthenticationException userAuthenticationException = null;
		UserProfile userAccountProfile = null;
		try {
			userAccountProfile = authorizationService.authenticate(token);
		} catch (AuthenticationException uae) {
			userAuthenticationException = (CorporateAuthenticationException) uae;
		}

		if (null != userAccountProfile)
			return new UsernamePasswordAuthenticationToken(token, CommonUtility.STRING_BLANK, userAccountProfile.getUserAccount().getAuthorities());			

		throw userAuthenticationException;
	}
}
