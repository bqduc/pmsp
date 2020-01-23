package net.paramount.service.email;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import net.paramount.framework.component.ComponentBase;
import net.paramount.service.mailing.Mail;

@Component
public class MailServiceHelper extends ComponentBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7426015807103285508L;

	@Inject
	private JavaMailSender sender;

	@Inject
	private Configuration freemarkerConfig;

	public void sendEmail(Mail mail) throws Exception {
		MimeMessage message = sender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message);

		// Using a subfolder such as /templates here
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), getTemplateLoadingDir());

		Template t = freemarkerConfig.getTemplate("/auth/forgotPassword.ftl");
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());

		helper.setTo(mail.getMailTo());
		helper.setText(text, true);
		helper.setSubject(mail.getSubject());

		sender.send(message);
	}

	public String getTemplateLoadingDir() {
		return "/emailTemplate/";
	}
}
