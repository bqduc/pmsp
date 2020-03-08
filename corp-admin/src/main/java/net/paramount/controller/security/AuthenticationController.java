/**
 * 
 */
package net.paramount.controller.security;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import com.github.adminfaces.template.config.AdminConfig;

import net.paramount.auth.entity.UserAccount;
import net.paramount.comm.comp.Communicator;
import net.paramount.comm.comp.CommunicatorServiceHelper;
import net.paramount.comm.domain.CorpMimeMessage;
import net.paramount.common.CommonConstants;
import net.paramount.common.DateTimeUtility;
import net.paramount.exceptions.CommunicatorException;
import net.paramount.framework.controller.RootController;
import net.paramount.msp.util.Constants;

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
	private Communicator communicationService;

	@Inject
	private CommunicatorServiceHelper mailServiceHelper;
	
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
		//this.routePage(getLoginPageId(), true);
	}

	public void handleRegister() {
		DateTimeUtility.getTimezones();
		try {
			communicationService.sendEmail(CorpMimeMessage.builder().build());
		} catch (CommunicatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		onHandleRegister();
		this.routePage(getLoginPageId(), true);
	}

	private void onHandleForgotPassword() {
		CorpMimeMessage mail = new CorpMimeMessage();
		mail.setFrom("javabycode@gmail.com");
		mail.setRecipients(new String[] {requestEmail /*"ducbuiquy@gmail.com"*/});
		mail.setSubject("Spring Boot - Email with FreeMarker template");
 
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("firstName", "Duc");
		model.put("lastName", "Bui Quy");
		model.put("location", "Columbus");
		model.put("signature", "www.javabycode.com");
		mail.setDefinitions(model);
 
		try {
			mailServiceHelper.sendEmail(mail);
			//mailService.sendEmail(mail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void onHandleRegister() {
		CorpMimeMessage mail = new CorpMimeMessage();
		mail.setFrom("javabycode@gmail.com");
		mail.setRecipients(new String[] {"duc.buiquy@vn.bosch.com"});
		mail.setSubject("Admin-Spring Boot - Email with FreeMarker template");
		mail.setLocale(this.getCurrentLocale());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userContact", getUserAccount());
		model.put("firstName", "Đức");
		model.put("lastName", "Bùi Quy");
		model.put("location", "Binh Dinh-Sai Gon");
		model.put("signature", "www.mekongparadise.com");
		
		try {
			File imageFile = ResourceUtils.getFile("classpath:template/subscription/images/marker-icon.png");
			byte[] fileContent = FileUtils.readFileToByteArray(imageFile);
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
			String encodedfile = new String(Base64.getEncoder().encode(fileContent), CommonConstants.ENCODING_NAME_UTF8);
			
			String imgAsBase64 = "data:image/png;base64," + encodedfile;
			model.put("imgAsBase64", imgAsBase64);
			/*
			
			File img = ResourceUtils.getFile("classpath:template/subscription/images/marker-icon.png");
			fileContent = FileUtils.readFileToByteArray(img);
			encodedString = Base64.getEncoder().encodeToString(fileContent);
			encodedfile = new String(Base64.getEncoder().encode(fileContent), CommonConstants.ENCODING_NAME_UTF8);
			
			byte[] imgBytes = IOUtils.toByteArray(new FileInputStream(img));
			byte[] imgBytesAsBase64 = Base64.getEncoder().encode(imgBytes);
			String imgDataAsBase64 = new String(imgBytesAsBase64);
			imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
			
			
			InputStreamSource imageSource = new ByteArrayResource(IOUtils.toByteArray(getClass().getResourceAsStream("/template/subscription/images/marker-icon.png")));
			
			model.put("imgAsBase64", imageSource);*/
			
			//model.put("imageSpec", img);
			mail.setDefinitions(model);

			mailServiceHelper.setEmailTemplateLoadingDir("/template/");
			//mailServiceHelper.sendEmail(mail, "/auth/register.ftl");
			mailServiceHelper.sendEmail(mail, "/subscription/subscription.ftl");
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
