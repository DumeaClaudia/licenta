package com.license.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.license.CurrentCartRequest;
import com.license.User;
import com.license.restaurant.RestaurantService;
import com.license.shoppingCart.ShoppingCartService;
import com.license.user.UserService;

@ManagedBean
@SessionScoped
public class DeliveryDataBean implements Serializable {

	private static final long serialVersionUID = 5328232620616926269L;
	private Long userId;

	private CurrentCartRequest userData;
	private User currentUser;
	private List<String> paymentList = new ArrayList<>();

	@EJB
	private ShoppingCartService shoppingCartService;
	@EJB
	private RestaurantService restaurantService;
	@EJB
	private UserService userService;

	@PostConstruct
	public void init() {
		paymentList.add("Card Credit");
		paymentList.add("Card Debit");
		paymentList.add("Ramburs");
		
		userData = new CurrentCartRequest();
	}

	public long getUserId() {
		if (this.userIsLogged()) {
			return this.userId;
		}

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext extContext = context.getExternalContext();
		try {
			extContext.redirect(extContext.getRequestContextPath() + "/pages/home.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	private boolean userIsLogged() {
		if (userId == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			userId = (Long) context.getExternalContext().getSessionMap().get("userId");
		}

		return userId != null;
	}

	public void setPaymentList(List<String> paymentList) {
		this.paymentList = paymentList;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public CurrentCartRequest getUserData() {
		if (userIsLogged()) {
			currentUser = userService.getUserById(userId);

			if (userData.getFirstName() == null) {
				userData.setFirstName(currentUser.getFirstName());
			}
			if (userData.getLastName() == null) {
				userData.setLastName(currentUser.getLastName());
			}
			if (userData.getEmail() == null) {
				userData.setEmail(currentUser.getEmail());
			}
		}
		return userData;
	}

	public void setUserData(CurrentCartRequest userData) {
		this.userData = userData;
	}

	public List<String> getPaymentList() {
		return paymentList;
	}

}
