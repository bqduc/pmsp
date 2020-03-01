/**
 * 
 */
package net.paramount.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import net.paramount.ase.config.QuartzJobSchedulerConfiguration;
import net.paramount.global.GlobalConstants;

/**
 * @author bqduc
 *
 */
@EnableScheduling
@EnableCaching
@Configuration
@EnableJpaRepositories(basePackages = { GlobalConstants.QNS_PACKAGE })
@ComponentScan(basePackages = { GlobalConstants.QNS_PACKAGE })
@EntityScan(basePackages = { GlobalConstants.QNS_PACKAGE })
@EnableTransactionManagement
@Import(value = { AuditingConfiguration.class, SecurityConfig.class, QuartzJobSchedulerConfiguration.class })
public class BaseConfiguration {
/*	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(CommonUtility.LOCALE_VIETNAMESE); // change this
		return localeResolver;
	}*/
}
