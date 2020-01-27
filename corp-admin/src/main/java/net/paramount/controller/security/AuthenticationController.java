/**
 * 
 */
package net.paramount.controller.security;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.adminfaces.template.config.AdminConfig;

import net.paramount.auth.entity.UserAccount;
import net.paramount.common.DateTimeUtility;
import net.paramount.component.email.MailServiceHelper;
import net.paramount.framework.controller.RootController;
import net.paramount.msp.util.Constants;
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

	/*@Inject
	private MailService mailService;*/
	
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

	public void handleForgotPassword() {
		onHandleForgotPassword();
		this.routePage(getLoginPageId(), true);
	}

	public void handleRegister() {
		onHandleRegister();
		this.routePage(getLoginPageId(), true);
	}

	private void onHandleForgotPassword() {
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
	
	private void onHandleRegister() {
		Mail mail = new Mail();
		mail.setMailFrom("javabycode@gmail.com");
		mail.setMailTo("ducbuiquy@gmail.com");
		mail.setSubject("Admin-Spring Boot - Email with FreeMarker template");
 
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userContact", getUserAccount());
		model.put("firstName", "Đức");
		model.put("lastName", "Bùi Quy");
		model.put("location", "Binh Dinh-Sai Gon");
		model.put("signature", "www.mekongparadise.com");
		mail.setModel(model);
 
		try {
			mailServiceHelper.setEmailTemplateLoadingDir("/emailTemplate/");
			mailServiceHelper.sendEmail(mail, "/auth/register.ftl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private UserAccount getUserAccount() {
		UserAccount userAccount = UserAccount.builder()
				.firstName("Trụi")
				.lastName("Trần Văn")
				.build();

		Date systemDate = DateTimeUtility.getSystemDate();
		userAccount.setEmail("trui.tranvan@mekongparadise.com");
		userAccount.setCompanyName("Sóng Giang");
		userAccount.setOccupationCode("XDSX");
		userAccount.setPhoneNumber("+xs 909-0290291");
		userAccount.setStateProvince("Bình Dương-Bà Rịa Vũng Tàu");
		userAccount.setCountryCode("+84");
		userAccount.setRegisteredDate(systemDate);
		userAccount.setIssueDate(DateTimeUtility.add(systemDate, Calendar.DAY_OF_MONTH, 5));
		userAccount.setActivationDate(DateTimeUtility.add(systemDate, Calendar.DAY_OF_MONTH, 10));
		userAccount.setApprovedDate(DateTimeUtility.add(systemDate, Calendar.DAY_OF_MONTH, 15));
		return userAccount;
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
