package net.paramount.msp.service;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import net.paramount.service.mailing.Mail;

@Service
public class MailService {
	@Inject
	private JavaMailSender sender;

	@Inject
	private Configuration freemarkerConfig;

	public void sendEmail(Mail mail) throws Exception {
		MimeMessage message = sender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message);

		// Using a subfolder such as /templates here
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/emailTemplate/auth/");

		Template t = freemarkerConfig.getTemplate("forgotPassword.ftl");
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());

		helper.setTo(mail.getMailTo());
		helper.setText(text, true);
		helper.setSubject(mail.getSubject());

		sender.send(message);
	}
}
