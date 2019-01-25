package com.license.data;

import java.util.List;

public class RestaurantProductsItem {
	long restaurantId;
	String restaurantName;
	String restaurantImage;
	String restaurantAddress;
	String restaurantGeolocation;

	List<ProductDetailsItem> products;

	public RestaurantProductsItem(long restaurantId, String restaurantName, String restaurantImage,
			String restaurantAddress, String restaurantGeolocation,  List<ProductDetailsItem> products) {
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantImage = restaurantImage;
		this.restaurantAddress = restaurantAddress;
		this.restaurantGeolocation = restaurantGeolocation;
		this.products = products;
	}

	public long getRestaurantId() {
		return restaurantId;
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

	public String getRestaurantGeolocation() {
		return restaurantGeolocation;
	}

	public List<ProductDetailsItem> getProducts() {
		return products;
	}

}
