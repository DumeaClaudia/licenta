package com.license.restaurant;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.license.Product;
import com.license.Restaurant;
import com.license.repositories.restaurant.RestaurantRepository;

@Remote(RestaurantService.class)
@Stateless
public class RestaurantServiceImpl implements RestaurantService {

	@EJB
	private RestaurantRepository repository;

	public List<Restaurant> getRestaurants() {
		return repository.retrieveRestaurants();
	}

	public Restaurant getRestaurantById(long id) {
		return repository.retrieveRestaurantById(id);
	}
	
	public List<Product> getAllProductsForRestaurant(long idRestaurant){
		return repository.retrieveAllProducts(idRestaurant);
	}
	
	public List<Product> getProductsByCategory(String category, long idRestaurant){
		return repository.retrieveProductsByCategory(category, idRestaurant);
	}

	public List<String> getCategoriesOfProducts(long idRestaurant) {
		return repository.retrieveCategories(idRestaurant);
	}

}
