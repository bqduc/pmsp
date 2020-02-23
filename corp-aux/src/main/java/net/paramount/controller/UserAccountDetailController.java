/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 
package net.paramount.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;
import org.springframework.security.access.AccessDeniedException;

import com.github.adminfaces.template.util.Assert;

import net.paramount.auth.entity.UserAccount;
import net.paramount.auth.service.UserAccountService;
import net.paramount.utility.FacesUtilities;

*//**
 * @author ducbq
 *//*
@Named(value = "userAccountDetail")
@ViewScoped
public class UserAccountDetailController implements Serializable {
	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1654144183269646576L;

	@Inject
	private UserAccountService businessService;

	@Inject
	private FacesUtilities facesUtilities;

	private Long id;
	private UserAccount businessObject;

	public void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}
		if (Assert.has(id)) {
			businessObject = businessService.getObject(id);
		} else {
			businessObject = new UserAccount();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserAccount getBusinessObject() {
		return businessObject;
	}

	public void setBusinessObject(UserAccount businessObject) {
		this.businessObject = businessObject;
	}

	public void remove() throws IOException {
		if (!facesUtilities.isUserInRole("ROLE_ADMIN")) {
			throw new AccessDeniedException("User not authorized! Only role <b>admin</b> can remove cars.");
		}
		if (Assert.has(businessObject) && Assert.has(businessObject.getId())) {
			businessService.remove(businessObject);
			facesUtilities.addDetailMessage("Business object " + businessObject.getDisplayName() + " removed successfully");
			Faces.getFlash().setKeepMessages(true);
			Faces.redirect("user/car-list.jsf");
		}
	}

	public void save() {
		String msg;
		if (businessObject.getId() == null) {
			businessService.saveOrUpdate(businessObject);
			msg = "Business object " + businessObject.getDisplayName() + " created successfully";
		} else {
			businessService.saveOrUpdate(businessObject);
			msg = "Business object " + businessObject.getDisplayName() + " updated successfully";
		}
		facesUtilities.addDetailMessage(msg);
	}

	public void clear() {
		businessObject = new UserAccount();
		id = null;
	}

	public boolean isNew() {
		return businessObject == null || businessObject.getId() == null;
	}

	public void handleParentSelect(SelectEvent event) { 
		Object item = event.getObject(); 
		if (item instanceof UserAccount) {
			this.parent = (UserAccount)item;
		}
		//FacesMessage msg = new FacesMessage("Selected", "Item:" + item); 
	}

}
*/