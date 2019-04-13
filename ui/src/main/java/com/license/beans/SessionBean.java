package com.license.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class SessionBean {
	Long userId;
	String username = "";
	
	public String getUsername() {	
		FacesContext context = FacesContext.getCurrentInstance();
		username = (String) context.getExternalContext().getSessionMap().get("username");
		return username;
	}

	public Long getUserId() {
		FacesContext context = FacesContext.getCurrentInstance();
		userId = (Long) context.getExternalContext().getSessionMap().get("userId");
		return userId;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().clear();
		return "home.xhtml?faces-redirect=true";
	}
}
