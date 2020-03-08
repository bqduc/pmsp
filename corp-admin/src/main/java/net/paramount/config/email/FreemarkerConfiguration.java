/**
 * 
 */
package net.paramount.config.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import net.paramount.common.CommonConstants;

/**
 * @author ducbq
 *
 */
@Configuration
//@EnableWebMvc
public class FreemarkerConfiguration {
	@Bean
	public FreeMarkerConfigurer freemarkerConfig() {
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		//freeMarkerConfigurer.setTemplateLoaderPath("/emailTemplate/");
		freeMarkerConfigurer.setDefaultEncoding(CommonConstants.ENCODING_NAME_UTF8);
		return freeMarkerConfigurer;
	}

	@Bean
	public FreeMarkerViewResolver freemarkerViewResolver() { 
	    FreeMarkerViewResolver resolver = new FreeMarkerViewResolver(); 
	    resolver.setCache(true); 
	    resolver.setPrefix(""); 
	    resolver.setSuffix(".ftl"); 
	    return resolver; 
	}
}
