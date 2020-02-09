/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paramount.controller.paux;

import static com.github.adminfaces.template.util.Assert.has;

import java.io.IOException;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;

import com.github.adminfaces.template.exception.AccessDeniedException;

import net.paramount.auth.entity.UserAccount;
import net.paramount.auth.service.UserAccountService;
import net.paramount.common.CommonConstants;
import net.paramount.entity.general.BusinessUnit;
import net.paramount.framework.controller.RootController;
import net.paramount.utility.FacesUtilities;

/**
 * @author ducbq
 */
@Named(value = "userAccountRegister")
@ViewScoped
public class UserAccountRegister extends RootController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 122890735173420046L;

	@Inject
	private UserAccountService businessService;

	@Inject
	private FacesUtilities utils;

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

	public void save() {
		preProcessUserAccount();
		if (!this.validate()) {
			utils.addDetailMessage(messageSource.getMessage("msg.userAccountRegisterFailure", new Object[] {this.entity.getEmail()}, super.getCurrentLocale()));
			Faces.getFlash().setKeepMessages(true);
			return;
		}

		this.entity.setBusinessUnitCode(this.businessUnit.getCode());
		businessService.registerUserAccount(this.entity);
		utils.addDetailMessage(messageSource.getMessage("msg.userAccountRegisterSuccess", new Object[] {this.entity.getEmail()}, super.getCurrentLocale()));
		Faces.getFlash().setKeepMessages(true);
		try {
			Faces.redirect("index.jsf");
		} catch (IOException e) {
			log.error(e);;
		}
	}

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
}