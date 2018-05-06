package com.license.beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.license.User;
import com.license.services.UserService;

@ManagedBean
// @RequestScoped
@SessionScoped
public class UserBean {

	private String username;
	private String password;
	private long idUser;

	private User user = new User();

	@EJB
	private UserService userService;
	
	public String login() {
		System.out.println("called login method");

		User userDB = new User();
		userDB = userService.login(username, password);
		idUser = userDB.getId();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("username", userDB.getUsername());
		context.getExternalContext().getSessionMap().put("userId", userDB.getId());
		
		if (!(userDB == null)) {
			return "activity?faces-redirect=true";
		} else {
			FacesContext context2 = FacesContext.getCurrentInstance();
			context2.addMessage(null, new FacesMessage("Wrong username/password"));
			context2.getExternalContext().getFlash().setKeepMessages(true);
			return "home?faces-redirect=true";
		}

	}

	public String register() {
		System.out.println("called register method");
		userService.register(user);
		return "login?faces-redirect=true";
	}
	
	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setName(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}