package com.license.beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.license.data.CartDetailsItem;
import com.license.restaurant.RestaurantService;
import com.license.shoppingCart.ShoppingCartService;

@ManagedBean
@SessionScoped
public class ShoppingCartBean implements Serializable{
	
	private static final long serialVersionUID = 8618627595602029032L;

	private Long userId;

	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String address;
	private String payment;

	@EJB
	private ShoppingCartService shoppingCartService;
	@EJB
	private RestaurantService restaurantService;

	public CartDetailsItem getCartDetails() {
		List<Long> cartIdsUser = null;

		FacesContext context = FacesContext.getCurrentInstance();
		userId = (Long) context.getExternalContext().getSessionMap().get("userId");

		long currentCartId = 0;
		if (userId != null) {
			cartIdsUser = shoppingCartService.getAllShoppingCartsForUser(userId);
			currentCartId = shoppingCartService.getCurrentCart(userId);	
		}
		if (userId != null && currentCartId != 0 ) {//&& cartIdsUser.contains(currentCartId)) {
			return CartDetailsItem.getCartDetailsItem(shoppingCartService, restaurantService, userId, currentCartId);
		}

		return new CartDetailsItem();
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String checkout() {
		return "shoppingCart?faces-redirect=true";
	}

	public String continueCheckout() {
		// TODO aici ar trebui sa setez sendDate si abia acum sa apara in history
		// page

		Long currentCartId = shoppingCartService.getCurrentCart(userId);

		if (currentCartId != null) {
			shoppingCartService.updateDateForCartAfterCheckout(userId, currentCartId);
		}
		// si sa resetez cosul..

		return "home?faces-redirect=true";

	}
}
