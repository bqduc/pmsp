package net.paramount.ase.controller;
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

import net.paramount.auth.entity.Authority;
import net.paramount.auth.service.AuthorityService;
import net.paramount.utility.FacesUtilities;

*//**
 * @author ducbq
 *//*
@Named(value = "authorityDetail")
@ViewScoped
public class AuthorityDetail implements Serializable {
	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 6209572587305925873L;

	@Inject
	private AuthorityService businessService;

	@Inject
	private FacesUtilities facesUtilities;

	private Long id;
	private Authority bizEntity;
	private Authority parent;

	public void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}
		if (Assert.has(id)) {
			bizEntity = businessService.getObject(id);
		} else {
			bizEntity = new Authority();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void remove() throws IOException {
		if (!facesUtilities.isUserInRole("ROLE_ADMIN")) {
			throw new AccessDeniedException("User not authorized! Only role <b>admin</b> can remove cars.");
		}
		if (Assert.has(bizEntity) && Assert.has(bizEntity.getId())) {
			businessService.remove(bizEntity);
			facesUtilities.addDetailMessage("Business object " + bizEntity.getName() + " removed successfully");
			Faces.getFlash().setKeepMessages(true);
			Faces.redirect("user/car-list.jsf");
		}
	}

	public void save() {
		String msg;
		bizEntity.setParent(parent);
		if (bizEntity.getId() == null) {
			businessService.saveOrUpdate(bizEntity);
			msg = "Business object " + bizEntity.getName() + " created successfully";
		} else {
			businessService.saveOrUpdate(bizEntity);
			msg = "Business object " + bizEntity.getName() + " updated successfully";
		}
		facesUtilities.addDetailMessage(msg);
	}

	public void clear() {
		bizEntity = new Authority();
		id = null;
	}

	public boolean isNew() {
		return bizEntity == null || bizEntity.getId() == null;
	}

	public void handleParentSelect(SelectEvent event) { 
		Object item = event.getObject(); 
		if (item instanceof Authority) {
			this.parent = (Authority)item;
		}
		//FacesMessage msg = new FacesMessage("Selected", "Item:" + item); 
	}

	public Authority getBizEntity() {
		return bizEntity;
	}

	public void setBizEntity(Authority bizEntity) {
		this.bizEntity = bizEntity;
	}
}
*/