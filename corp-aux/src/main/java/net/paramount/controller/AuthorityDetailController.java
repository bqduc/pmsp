/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

import net.paramount.auth.entity.Authority;
import net.paramount.auth.service.AuthorityService;
import net.paramount.utility.FacesUtilities;

/**
 * @author ducbq
 */
@Named(value = "authorityDetailController")
@ViewScoped
public class AuthorityDetailController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6209572587305925873L;

	@Inject
	private AuthorityService businessService;

	@Inject
	private FacesUtilities facesUtilities;

	private Long id;
	private Authority businessObject;
	private Authority parent;

	public void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}
		if (Assert.has(id)) {
			businessObject = businessService.getObject(id);
		} else {
			businessObject = new Authority();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Authority getBusinessObject() {
		return businessObject;
	}

	public void setBusinessObject(Authority businessObject) {
		this.businessObject = businessObject;
	}

	public void remove() throws IOException {
		if (!facesUtilities.isUserInRole("ROLE_ADMIN")) {
			throw new AccessDeniedException("User not authorized! Only role <b>admin</b> can remove cars.");
		}
		if (Assert.has(businessObject) && Assert.has(businessObject.getId())) {
			businessService.remove(businessObject);
			facesUtilities.addDetailMessage("Business object " + businessObject.getName() + " removed successfully");
			Faces.getFlash().setKeepMessages(true);
			Faces.redirect("user/car-list.jsf");
		}
	}

	public void save() {
		String msg;
		businessObject.setParent(parent);
		if (businessObject.getId() == null) {
			businessService.saveOrUpdate(businessObject);
			msg = "Business object " + businessObject.getName() + " created successfully";
		} else {
			businessService.saveOrUpdate(businessObject);
			msg = "Business object " + businessObject.getName() + " updated successfully";
		}
		facesUtilities.addDetailMessage(msg);
	}

	public void clear() {
		businessObject = new Authority();
		id = null;
	}

	public boolean isNew() {
		return businessObject == null || businessObject.getId() == null;
	}

	public void handleParentSelect(SelectEvent event) { 
		Object item = event.getObject(); 
		if (item instanceof Authority) {
			this.parent = (Authority)item;
		}
		//FacesMessage msg = new FacesMessage("Selected", "Item:" + item); 
	}

}
