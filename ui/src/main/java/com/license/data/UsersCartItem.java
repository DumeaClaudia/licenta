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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartDetails == null) ? 0 : cartDetails.hashCode());
		result = prime * result + ((userDetails == null) ? 0 : userDetails.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsersCartItem other = (UsersCartItem) obj;
		if (cartDetails == null) {
			if (other.cartDetails != null)
				return false;
		} else if (!cartDetails.equals(other.cartDetails))
			return false;
		if (userDetails == null) {
			if (other.userDetails != null)
				return false;
		} else if (!userDetails.equals(other.userDetails))
			return false;
		return true;
	}
	
	
}
