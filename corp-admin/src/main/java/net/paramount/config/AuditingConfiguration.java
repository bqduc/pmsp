/**
 * 
 */
package net.paramount.config;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import net.paramount.auth.service.AuthorizationService;
import net.paramount.autx.SecurityServiceContextHelper;

/**
 * @author ducbq
 *
 */
@Configuration
@EnableJpaAuditing
public class AuditingConfiguration {
	@Inject 
	private AuthorizationService authorizationService;
	
	@Inject
	private SecurityServiceContextHelper securityContextHolderServiceHelper;

	@Bean
	public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}

	class AuditorAwareImpl implements AuditorAware<String> {
		@Override
		public Optional<String> getCurrentAuditor() {
			System.out.println("Authenticated user from security officer component: " + authorizationService.getUserProfile());
			Authentication auth = securityContextHolderServiceHelper.getAuthentication();
			if (auth.getPrincipal() instanceof String) {
				return Optional.of((String)auth.getPrincipal());
			}

			User authUser = (User)auth.getPrincipal();
			return Optional.of(authUser.getUsername());
			/*
			Optional<User> optLoggedInUser = this.getCurrentLoggedInUser();
			if (optLoggedInUser.isPresent())
				return Optional.of(optLoggedInUser.get().getUsername());

			return Optional.of("ANNO");//Annonymous
			*/
		}

		private Optional<User> getCurrentLoggedInUser() {
			Authentication auth = securityContextHolderServiceHelper.getAuthentication();
			if (null != auth) {
				return Optional.of((User)auth.getPrincipal());
			}
			return Optional.empty();
		}
	}
}
