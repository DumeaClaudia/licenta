package com.license.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class SessionBean  implements Serializable{

	private static final long serialVersionUID = -5849490265447792901L;
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
		return "home?faces-redirect=true";
		
		
		
	}
}
