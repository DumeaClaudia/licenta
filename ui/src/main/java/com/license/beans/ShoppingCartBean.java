package com.license.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.license.data.CartDetailsItem;
import com.license.restaurant.RestaurantService;
import com.license.shoppingCart.ShoppingCartService;

@ManagedBean
@SessionScoped
/*@RequestScoped*/
public class ShoppingCartBean implements Serializable {

	private static final long serialVersionUID = 8618627595602029032L;

	private Long userId;

	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String address;
	private String payment = new String("Ramburs");
	private List<String> paymentList = new ArrayList<>();

    @PostConstruct
    public void init() {
    	paymentList.add("Ramburs");
    	paymentList.add("Card Debit");
    	paymentList.add("Card Credit");
    }


	@EJB
	private ShoppingCartService shoppingCartService;
	@EJB
	private RestaurantService restaurantService;

	public CartDetailsItem getCartDetails() {
		FacesContext context = FacesContext.getCurrentInstance();
		userId = (Long) context.getExternalContext().getSessionMap().get("userId");

		long currentCartId = 0;
		if (userId != null) {
			currentCartId = shoppingCartService.getCurrentCart(userId);
			if (currentCartId != 0) {
				return CartDetailsItem.getCartDetailsItem(shoppingCartService, restaurantService, userId,
						currentCartId);
			}
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

	public List<String> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<String> paymentList) {
		this.paymentList = paymentList;
	}

	public String checkout() {
		return "shoppingCart?faces-redirect=true";
	}
}
