package com.license.data;

public class CartItem {
	
	String createdDate;
    String restaurantName;
    
	public CartItem( String restaurantName, String createdDate) {
		super();
		this.createdDate = createdDate;
		this.restaurantName = restaurantName;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getRestaurantName() {
		return restaurantName;
	}
    

}
