package net.paramount.msp.config;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import net.paramount.auth.domain.UserAccountProfile;
import net.paramount.auth.service.AuthorizationService;
import net.paramount.common.ListUtility;

/**
 * Created by aLeXcBa1990 on 24/11/2018.
 * 
 */

@Component
public class CorporateAuthenticationProvider implements AuthenticationProvider {
	@Inject 
	private AuthorizationService authorizationService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication repAuthentication = null;
		String user = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserAccountProfile userAccountProfile = null;
		try {
			userAccountProfile = authorizationService.authenticate(user, password);
			System.out.println("Authenticate result: " + userAccountProfile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Here you can add a database connection for your custom login  
		List<GrantedAuthority> grantedAuthorities = ListUtility.createDataList();

		if (user.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")){
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else if (user.equalsIgnoreCase("user") && password.equalsIgnoreCase("user")){
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}

		if (grantedAuthorities.size() > 0) {
			repAuthentication = new UsernamePasswordAuthenticationToken(user, password, grantedAuthorities);
		}
			
		return repAuthentication;
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {

		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));

	}
	
}
