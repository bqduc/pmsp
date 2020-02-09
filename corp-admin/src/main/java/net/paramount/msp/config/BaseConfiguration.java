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
//@Slf4j
@EnableScheduling
@EnableCaching
@Configuration
@EnableJpaRepositories(basePackages = { "net.paramount" })
@ComponentScan(basePackages = { "net.paramount" })
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
/*
	@Bean
  public MessageSource messageSource() {
  	String[] resourceBundles = new String[]{
  			"classpath:/i18n/admin/AdminMessages", 
  			"classpath:/i18n/admin/Messages_aux_en", 

  			"classpath:/i18n/general/Messages-general", 
  			"classpath:/i18n/general/Messages_ctdmx", 
  			"classpath:/i18n/general/Messages", 
  			"classpath:/i18n/general/Messages_master", 
  			"classpath:/i18n/general/Messages_menu", 
  			"classpath:/i18n/general/Messages_user_profile", 
  			"classpath:/i18n/general/ValidationMessages", 
  			
  			"classpath:/i18n/mvp/Messages_mvp_general", 

  			"classpath:/i18n/general/Messages_definition", 
    		"classpath:/i18n/general/Messages_menu"
    };
  	
  	//log.info("Initialize the message source......");
  	
  	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
      messageSource.setBasenames(resourceBundles);
      messageSource.setDefaultEncoding("UTF-8");
      return messageSource;
  }
	*/
	/*
	@Bean
  public MessageSource messageSource() {
  	MessageSource messageSource = new DBMessageSource();
    return messageSource;
  }
	*/
	/*
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setCacheSeconds(10); // reload messages every 10 seconds
		return messageSource;
	}
	*/
	
}
