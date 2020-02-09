/**
 * 
 */
package net.paramount.msp.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author ducbq
 *
 */
@Configuration
@EnableJpaAuditing
public class AuditingConfiguration {
	@Bean
	public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}

	class AuditorAwareImpl implements AuditorAware<String> {
		@Override
		public Optional<String> getCurrentAuditor() {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
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
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (null != auth) {
				return Optional.of((User)auth.getPrincipal());
			}
			return Optional.empty();
		}
	}
}
