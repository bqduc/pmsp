/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paramount.dbx.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;
import org.springframework.security.access.AccessDeniedException;

//import com.github.adminfaces.template.util.Assert;

import net.paramount.dbx.entity.Dashboard;
import net.paramount.dbx.service.DashboardService;
import net.paramount.utility.FacesUtilities;

/**
 * @author ducbq
 */
@Named(value = "dashletDetailController")
@ViewScoped
public class DashletDetailController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1432620161803381120L;

	@Inject
	private DashboardService businessService;

	@Inject
	private FacesUtilities facesUtilities;

	private Long id;
	private Dashboard businessObject;
	private Dashboard parent;

	public void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}
		/*if (Assert.has(id)) {
			businessObject = businessService.getObject(id);
		} else {
			businessObject = new Dashboard();
		}*/
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Dashboard getBusinessObject() {
		return businessObject;
	}

	public void setBusinessObject(Dashboard businessObject) {
		this.businessObject = businessObject;
	}

	public void remove() throws IOException {
		if (!facesUtilities.isUserInRole("ROLE_ADMIN")) {
			throw new AccessDeniedException("User not authorized! Only role <b>admin</b> can remove cars.");
		}
		/*if (Assert.has(businessObject) && Assert.has(businessObject.getId())) {
			businessService.remove(businessObject);
			facesUtilities.addDetailMessage("Business object " + businessObject.getName() + " removed successfully");
			Faces.getFlash().setKeepMessages(true);
			Faces.redirect("user/car-list.jsf");
		}*/
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
		businessObject = new Dashboard();
		id = null;
	}

	public boolean isNew() {
		return businessObject == null || businessObject.getId() == null;
	}

	public void handleParentSelect(SelectEvent event) { 
		Object item = event.getObject(); 
		if (item instanceof Dashboard) {
			this.parent = (Dashboard)item;
		}
		//FacesMessage msg = new FacesMessage("Selected", "Item:" + item); 
	}

}
