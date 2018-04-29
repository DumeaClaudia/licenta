package com.license.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import com.license.Restaurant;
import com.license.services.RestaurantService;

@ManagedBean
@RequestScoped
//@SessionScoped
public class RestaurantBean {

/*	private String name;
	private String description;
	private int stars;
	private String street;*/
	private Restaurant restaurant = null;
	private String details = "ceva";
	private long id;
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public List<Restaurant> restaurants  = new ArrayList<Restaurant>();

	@EJB
	private RestaurantService restaurantService;
	
	@PostConstruct
	void init() {

		restaurants = restaurantService.displayRestaurants();
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		for(Restaurant r:  restaurants) {
			if(r.getId() == id) {
				restaurant = r;
			}
		}
		this.id = id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	

}