/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paramount.controller.paux;

import static com.github.adminfaces.template.util.Assert.has;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.FileUtils;
import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;
import org.springframework.util.ResourceUtils;

import com.github.adminfaces.template.exception.AccessDeniedException;

import net.paramount.auth.entity.UserAccount;
import net.paramount.auth.service.AuthorizationService;
import net.paramount.auth.service.UserAccountService;
import net.paramount.comm.domain.CorpMimeMessage;
import net.paramount.comm.global.CommunicatorConstants;
import net.paramount.common.CommonConstants;
import net.paramount.common.ListUtility;
import net.paramount.css.service.config.ConfigurationService;
import net.paramount.entity.config.Configuration;
import net.paramount.entity.general.BusinessUnit;
import net.paramount.exceptions.MspRuntimeException;
import net.paramount.framework.controller.RootController;
import net.paramount.framework.model.Context;
import net.paramount.framework.model.ExecutionContext;
import net.paramount.global.GlobalConstants;
import net.paramount.utility.FacesUtilities;

/**
 * @author ducbq
 */
@Named//(value = "userAccountRegister")
@FlowScoped("userAccountRegister")
public class UserAccountRegister extends RootController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 122890735173420046L;

	@Inject
	private UserAccountService businessService;

	@Inject
	private FacesUtilities utils;

	@Inject 
	private AuthorizationService authorizationService;

	@Inject 
	private ConfigurationService configurationService;

	private Long id;

	private BusinessUnit businessUnit;
	private UserAccount entity;

	public void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}
		if (has(id)) {
			this.entity = businessService.getObject(Long.valueOf(id));
		} else {
			this.entity = UserAccount.builder().build();
		}
	}

	public void remove() throws IOException {
		if (!utils.isUserInRole("ROLE_ADMIN")) {
			throw new AccessDeniedException("User not authorized! Only role <b>admin</b> can remove cars.");
		}
		if (has(entity) && has(entity.getId())) {
			//businessService.remove(entity);
			utils.addDetailMessage("Car " + entity.getSsoId() + " removed successfully");
			Faces.getFlash().setKeepMessages(true);
			//Faces.redirect("user/car-list.jsf");
		}
	}

	public void register() {
		try {
			preProcessUserAccount();
			/*
			if (!this.validate()) {
				utils.addDetailMessage(persistenceMessageSource.getMessage("msg.userAccountRegisterFailure", new Object[] {this.entity.getEmail()}, super.getCurrentLocale()));
				Faces.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "register");
				return;
			}
			*/

			if (this.businessUnit != null) {
				this.entity.setCompanyName(this.businessUnit.getName());
			}
			
			this.entity.setStateProvince("Sài Gòn");

			this.entity.setBusinessUnitCode(this.businessUnit.getCode());

			ExecutionContext context = ExecutionContext.builder().build();
			
			context.put(CommunicatorConstants.CTX_MAIL_TEMPLATE_DIR, "/template/");
			context.put(CommunicatorConstants.CTX_MAIL_TEMPLATE_ID, "/auth/register.ftl");
			context.put(CommunicatorConstants.CTX_USER_ACCOUNT, this.entity);
			this.buildRegistrationContext(context);

			this.authorizationService.register(context);
			//businessService.registerUserAccount(this.entity);
			utils.addDetailMessage(persistenceMessageSource.getMessage("msg.userAccountRegisterSuccess", new Object[] {this.entity.getEmail()}, super.getCurrentLocale()));
			Faces.getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "index");
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	/*public void save() {
		preProcessUserAccount();
		if (!this.validate()) {
			utils.addDetailMessage(dbMessageSource.getMessage("msg.userAccountRegisterFailure", new Object[] {this.entity.getEmail()}, super.getCurrentLocale()));
			Faces.getFlash().setKeepMessages(true);
			return;
		}

		this.entity.setBusinessUnitCode(this.businessUnit.getCode());
		businessService.registerUserAccount(this.entity);
		utils.addDetailMessage(dbMessageSource.getMessage("msg.userAccountRegisterSuccess", new Object[] {this.entity.getEmail()}, super.getCurrentLocale()));
		Faces.getFlash().setKeepMessages(true);
		try {
			Faces.redirect("index.jsf");
		} catch (IOException e) {
			log.error(e);;
		}
	}*/

	private void preProcessUserAccount() {
		if (null==this.entity.getId()){
			int latDotPos = this.entity.getEmail().lastIndexOf(CommonConstants.DOT_SEPARATOR);
			String ssoId = this.entity.getEmail().substring(0, latDotPos)
					.replace(CommonConstants.DOT_SEPARATOR, CommonConstants.STRING_BLANK)
					.replace(CommonConstants.AT_SIGN, CommonConstants.STRING_BLANK);
			this.entity.setSsoId(ssoId);
		}
	}

	public void clear() {
		this.entity = UserAccount.builder().build();
		id = null;
	}

	public boolean isNew() {
		return this.entity == null || this.entity.getId() == null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserAccount getEntity() {
		return entity;
	}

	public void setEntity(UserAccount entity) {
		this.entity = entity;
	}

	public void handleBusinessUnitSelect(SelectEvent event) { 
		Object selectedObject = event.getObject(); 
		if (selectedObject instanceof BusinessUnit) {
			this.setBusinessUnit((BusinessUnit)selectedObject);
			this.entity.setCompanyName(this.businessUnit.getName());
			this.entity.setBusinessUnitCode(this.businessUnit.getCode());
		}
		//FacesMessage msg = new FacesMessage("Selected", "Item:" + item); 
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

	protected boolean validate() {
		if (null==this.entity.getId() && this.businessService.existsByEmail(this.entity.getEmail()))
			return false;

		return true;
	}

	private void buildRegistrationContext(Context context) {
		CorpMimeMessage corpMimeMessage = CorpMimeMessage.builder()
				.from("ducbuiquy@gmail.com")
				.subject(CommunicatorConstants.CTX_DEFAULT_REGISTRATION_SUBJECT) //Should get data from resource bundle for localization
				.locale(this.getCurrentLocale())
				.build();

		UserAccount userAccount = (UserAccount)context.get(CommunicatorConstants.CTX_USER_ACCOUNT);
		Map<String, Object> definitions = ListUtility.createMap();
		definitions.put("userContact", userAccount);
		definitions.put("location", "Bình Định-Sài Gòn");
		definitions.put("signature", "www.mekongparadise.com");

		try {
			File imageFile = ResourceUtils.getFile("classpath:template/images/marker-icon.png");
			byte[] fileContent = FileUtils.readFileToByteArray(imageFile);
			String encodedfile = new String(Base64.getEncoder().encode(fileContent), CommonConstants.ENCODING_NAME_UTF8);

			String imgAsBase64 = "data:image/png;base64," + encodedfile;
			definitions.put("imgAsBase64", imgAsBase64);
			
			definitions.put("lastName", userAccount.getLastName());
			definitions.put("firstName", userAccount.getFirstName());

			Optional<Configuration> opt = configurationService.getByName(GlobalConstants.CONFIG_APP_ACCESS_URL);
			if (!opt.isPresent())
				throw new MspRuntimeException("No configuration of application access link!");

			definitions.put(GlobalConstants.CONFIG_APP_ACCESS_URL, new StringBuilder(opt.get().getValue())
					.append("/protected/accountProfile/confirm/")
					.toString()
					);
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
			corpMimeMessage.setDefinitions(definitions);

			context.put(CommunicatorConstants.CTX_MIME_MESSAGE, corpMimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}