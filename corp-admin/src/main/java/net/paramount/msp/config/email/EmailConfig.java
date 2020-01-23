/**
 * 
 */
package net.paramount.msp.config.email;

import java.util.Properties;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * @author ducbq
 *
 */
@Component
@Configuration
public class EmailConfig {
	@Inject
	private EmailConfigProperties emailProperties;

	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(emailProperties.getHost());
		mailSender.setPort(Integer.parseInt(emailProperties.getPort()));
		mailSender.setUsername(emailProperties.getUsername());
		mailSender.setPassword(emailProperties.getPassword());

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");


		javaMailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		javaMailProperties.put("email.smtpStartTlsRequired", "true");
		javaMailProperties.put("email.transportProtocol", "smtp");
		javaMailProperties.put("email.encoding", "UTF-8");
				//mail.smtp.port=25, 

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}
}
