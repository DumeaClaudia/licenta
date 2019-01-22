package com.license.repositories.restaurant;

import java.util.List;

import com.license.Product;
import com.license.Restaurant;

public interface RestaurantRepository {
	public Restaurant retrieveRestaurantById(long id);
	public List<Restaurant> retrieveRestaurants();
	public List<Product> retrieveAllProducts(long idRestaurant);
	public List<Product> retrieveProductsByCategory(String category, long idRestaurant);
	public List<String> retrieveCategories(long idRestaurant);
	public Product retrieveProductById(long idProduct);
}

