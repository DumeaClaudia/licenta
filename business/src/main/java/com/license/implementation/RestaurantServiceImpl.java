package com.license.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.license.Product;
import com.license.Restaurant;
import com.license.repositories.restaurant.RestaurantRepository;
import com.license.services.RestaurantService;

@Remote(RestaurantService.class)
@Stateless
public class RestaurantServiceImpl implements RestaurantService {

	@EJB
	private RestaurantRepository repository;
	
	//List<Restaurant> restaurants = new ArrayList<Restaurant>();

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

}
