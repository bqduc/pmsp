package net.paramount.comm.component;

import java.nio.charset.StandardCharsets;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import net.paramount.comm.global.CommunicatorConstants;
import net.paramount.common.CommonUtility;
import net.paramount.exceptions.CommunicatorException;
import net.paramount.framework.component.ComponentBase;
import net.paramount.service.mailing.Mail;

@Component
public class MailServiceHelper extends ComponentBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7426015807103285508L;

	@Inject
	private JavaMailSender mailSender;

	@Inject
	private Configuration freemarkerConfig;
	
	private String emailTemplateLoadingDir;

	public void init() {
		//Maybe fetch all configuration entries from database or something like that
	}

	public void sendEmail(Mail mail) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message);

		// Using a sub-folder such as /templates here
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), getEmailTemplateLoadingDir());

		Template t = freemarkerConfig.getTemplate("/auth/forgotPassword.ftl");
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());

		helper.setTo(mail.getMailTo());
		helper.setText(text, true);
		helper.setSubject(mail.getSubject());

		mailSender.send(message);
	}

	public String getEmailTemplateLoadingDir() {
		return emailTemplateLoadingDir;
	}

	public void setEmailTemplateLoadingDir(String emailTemplateLoadingDir) {
		this.emailTemplateLoadingDir = emailTemplateLoadingDir;
	}

	public void sendEmail(Mail mail, String templateId) throws CommunicatorException {
		try {
			MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
          StandardCharsets.UTF_8.name());

			// Using a sub-folder such as /templates here
			String emailTemplateDir = this.getEmailTemplateLoadingDir();
			if (CommonUtility.isEmpty(emailTemplateDir)) {
				emailTemplateDir = CommunicatorConstants.DEFAULT_LOADING_TEMPLATE_DIRECTORY;
				log.info("The loading email template directory is empty, using the default email loading template: " + CommunicatorConstants.DEFAULT_LOADING_TEMPLATE_DIRECTORY);
			}
			freemarkerConfig.setClassForTemplateLoading(this.getClass(), emailTemplateDir);
			Template template = freemarkerConfig.getTemplate(templateId);

			helper.setSubject(mail.getSubject());
			helper.setTo(mail.getMailTo());
			String messageText = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
			helper.setText(messageText, true);

			/*StringWriter textWriter = new StringWriter();
			template.process(mail.getModel(), textWriter);
			String text = textWriter.toString();*/

			/*helper.addAttachment("pathToPortalImage", (File)mail.getModel().get("imageSpec"));
			
			if (CommonUtility.isNotEmpty(mail.getAttachments())) {
				for (Object attachment :mail.getAttachments()) {
					if (!(attachment instanceof EmailAttachment))
						continue;

					helper.addAttachment(((EmailAttachment)attachment).getName(), ((EmailAttachment)attachment).getInputStreamSource(), ((EmailAttachment)attachment).getContentType());
				}
			}*/
			mailSender.send(message);
		} catch (Exception e) {
			throw new CommunicatorException(e);
		}
	}
}
