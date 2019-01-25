package com.license.data;

import java.util.List;

public class RestaurantProductsItem {
	long restaurantId;
	String restaurantName;
	String restaurantImage;
	String restaurantAddress;

	List<ProductDetailsItem> products;

	public RestaurantProductsItem(long restaurantId, String restaurantName, String restaurantImage,
			String restaurantAddress, List<ProductDetailsItem> products) {
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantImage = restaurantImage;
		this.restaurantAddress = restaurantAddress;
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

	public List<ProductDetailsItem> getProducts() {
		return products;
	}

}
