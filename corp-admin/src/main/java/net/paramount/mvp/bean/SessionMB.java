package net.paramount.mvp.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.paramount.autx.SecurityServiceContextHelper;

@Named
@ViewScoped
public class SessionMB implements Serializable {

	private String currentUser;
	
	@Inject 
	private SecurityServiceContextHelper securityServiceContextHelper;

	@PostConstruct
	public void init() {
		currentUser = securityServiceContextHelper.getAuthenticationName();
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
}
