package com.license.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.license.Restaurant;
import com.license.restaurant.RestaurantService;

@ManagedBean
@SessionScoped
public class HomeRestaurantsListBean {

	public List<Restaurant> restaurants  = new ArrayList<Restaurant>();
	
	@EJB
	private RestaurantService restaurantService;
	
	@PostConstruct
	void init() {
		restaurants = restaurantService.getRestaurants();
	}
	
	public List<Restaurant>  displayAllRestaurants() {
		return this.restaurants;
	}
	
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
}