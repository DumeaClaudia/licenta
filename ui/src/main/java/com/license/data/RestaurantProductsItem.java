package com.license.data;

import java.util.List;

public class RestaurantProductsItem {
	String restaurantName;
	String restaurantImage;
	String restaurantAddress;
	
	List<ProductDetailsItem> products;

	public RestaurantProductsItem(String restaurantName, String restaurantImage, String restaurantAddress,
			List<ProductDetailsItem> products) {
		
		this.restaurantName = restaurantName;
		this.restaurantImage = restaurantImage;
		this.restaurantAddress = restaurantAddress;
		this.products = products;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public String getRestaurantImage() {
		return restaurantImage;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public List<ProductDetailsItem> getProducts() {
		return products;
	}
	
	
}
