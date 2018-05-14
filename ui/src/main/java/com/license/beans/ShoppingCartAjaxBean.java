package com.license.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.license.ShoppingCart;
import com.license.services.ShoppingCartService;

@ManagedBean
@ViewScoped
public class ShoppingCartAjaxBean {
	private String message;
	private String name;
	private String output;
	
	public String getOutput() {
		return this.output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void handleEvent(AjaxBehaviorEvent event) {
		this.output = "hello";
	}

}
