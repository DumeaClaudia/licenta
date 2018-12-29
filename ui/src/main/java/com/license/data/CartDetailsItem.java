package com.license.data;

import java.util.List;

public class CartDetailsItem {
	CartSummaryItem cartSummary;
	List<RestaurantProductsItem> restaurantProducts;
	
	public CartDetailsItem(CartSummaryItem cartSummary, List<RestaurantProductsItem> restaurantProducts) {
		this.cartSummary = cartSummary;
		this.restaurantProducts = restaurantProducts;
	}

	public CartSummaryItem getCartSummary() {
		return cartSummary;
	}

	public List<RestaurantProductsItem> getRestaurantProducts() {
		return restaurantProducts;
	}
}
