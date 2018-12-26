package com.license.restaurant;

import java.util.List;

import javax.ejb.Remote;

import com.license.Product;
import com.license.Restaurant;

@Remote
public interface RestaurantService {
	Restaurant getRestaurantById(long id);
	List<Restaurant> getRestaurants();
	List<Product> getAllProductsForRestaurant(long idRestaurant);
	List<Product> getProductsByCategory(String category, long idRestaurant);
	List<String> getCategoriesOfProducts(long idRestaurant);
}
