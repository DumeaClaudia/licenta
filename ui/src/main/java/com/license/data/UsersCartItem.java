package com.license.data;

public class UsersCartItem {
	
	private UserOrderItem userDetails;
	private CartDetailsItem cartDetails;
	
	public UserOrderItem getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserOrderItem userDetails) {
		this.userDetails = userDetails;
	}
	public CartDetailsItem getCartDetails() {
		return cartDetails;
	}
	public void setCartDetails(CartDetailsItem cartDetails) {
		this.cartDetails = cartDetails;
	}
}
