package com.license.data;

public class CartSummaryItem {
	
	String idCart;
	boolean cartActive;
	String createdDate;
    String cartDescription; // un restaurant => restaurant name, mai multe restaurant -> nr produse cart
    
	public CartSummaryItem(String idCart, String restaurantName, String createdDate) {
		this.idCart = idCart;
		this.createdDate = createdDate;
		this.cartDescription = restaurantName;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getRestaurantName() {
		return cartDescription;
	}

	public String getIdCart() {
		return idCart;
	}


}
