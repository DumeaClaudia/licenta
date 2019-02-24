package com.license.beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.license.User;
import com.license.user.UserService;
import com.license.utils.constants.EnumLogin;
import com.license.utils.exceptions.UserException;

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
	
	public String login(){
		FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_WARN, EnumLogin.LOGIN_ERROR.getConstant(),
				EnumLogin.INVALID_CREDENTIALS.getConstant()),
				success = new FacesMessage(FacesMessage.SEVERITY_INFO, EnumLogin.WELCOME.getConstant(), username);


		user = userService.login(username, password);		
		
		if(user==null) {
			FacesContext.getCurrentInstance().addMessage(null, error);
			return null;
		}
		idUser = user.getId();
			
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("username", user.getUsername());
		context.getExternalContext().getSessionMap().put("userId", user.getId());
		
		return null;
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