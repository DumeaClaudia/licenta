package com.license.data;

import java.util.List;

public class UserProductsItem {

	private String username;
	List<ProductDetailsCartItem> cartDetails;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<ProductDetailsCartItem> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(List<ProductDetailsCartItem> cartDetails) {
		this.cartDetails = cartDetails;
	}

	@Override
	public String toString() {
		return "UserProductsItem [username=" + username + ", cartDetails=" + cartDetails + ", getUsername()="
				+ getUsername() + ", getCartDetails()=" + getCartDetails() + ", toString()=" + super.toString() + "]";
	}

}
