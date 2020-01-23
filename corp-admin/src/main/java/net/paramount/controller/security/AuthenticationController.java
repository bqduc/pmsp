/**
 * 
 */
package net.paramount.controller.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.adminfaces.template.config.AdminConfig;

import net.paramount.framework.controller.RootController;
import net.paramount.msp.service.MailService;
import net.paramount.msp.util.Constants;
import net.paramount.service.email.MailServiceHelper;
import net.paramount.service.mailing.Mail;

/**
 * @author ducbq
 *
 */
@Named(value = "authenticator")
@ViewScoped
public class AuthenticationController extends RootController {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1102220100710484895L;

	@Inject
	private AdminConfig adminConfig;

	@Inject
	private MailService mailService;
	
	@Inject
	private MailServiceHelper mailServiceHelper;
	
	private String requestEmail;

	public void doLogout() throws IOException {
		this.routePage(getLoginPageId(), true);
	}

	public String getRequestEmail() {
		return requestEmail;
	}

	public void setRequestEmail(String requestEmail) {
		this.requestEmail = requestEmail;
	}

	public void handleForgotPasswordRequest() {
		handleForgotPassword();
		this.routePage(getLoginPageId(), true);
	}

	private void handleForgotPassword() {
		Mail mail = new Mail();
		mail.setMailFrom("javabycode@gmail.com");
		mail.setMailTo("ducbuiquy@gmail.com");
		mail.setSubject("Spring Boot - Email with FreeMarker template");
 
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("firstName", "Duc");
		model.put("lastName", "Bui Quy");
		model.put("location", "Columbus");
		model.put("signature", "www.javabycode.com");
		mail.setModel(model);
 
		try {
			mailServiceHelper.sendEmail(mail);
			//mailService.sendEmail(mail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getLoginPageId() {
		String loginPage = adminConfig.getLoginPage();
		if (loginPage == null || "".equals(loginPage)) {
			loginPage = Constants.DEFAULT_INDEX_PAGE;
		}
		
		if (!loginPage.startsWith("/")) {
			loginPage = "/" + loginPage;
		}
		return loginPage;
	}
}
