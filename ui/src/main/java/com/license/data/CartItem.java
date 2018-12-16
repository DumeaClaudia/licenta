package com.license.data;

public class CartItem {
	
	String idCart;
	String createdDate;
    String restaurantName;
    String restaurantImage;
    
	public CartItem(String idCart, String restaurantName, String createdDate, String restaurantImage) {
		this.idCart = idCart;
		this.createdDate = createdDate;
		this.restaurantName = restaurantName;
		this.restaurantImage = restaurantImage;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public String getIdCart() {
		return idCart;
	}

	public String getRestaurantImage() {
		return restaurantImage;
	}
}
