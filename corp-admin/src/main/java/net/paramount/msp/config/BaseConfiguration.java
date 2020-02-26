/**
 * 
 */
package net.paramount.msp.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import net.paramount.ase.config.MspQuartzConfig;
import net.paramount.common.CommonUtility;

/**
 * @author bqduc
 *
 */
@EnableScheduling
@EnableCaching
@Configuration
@EnableJpaRepositories(basePackages = { "net.paramount" })
@ComponentScan(basePackages = { "net.paramount"})
@EntityScan(basePackages = { "net.paramount" })
@EnableTransactionManagement
@Import(value = { AuditingConfiguration.class, SecurityConfig.class, MspQuartzConfig.class })
public class BaseConfiguration {
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(CommonUtility.LOCALE_VIETNAMESE); // change this
		return localeResolver;
	}
}
