package com.license.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.license.Restaurant;
import com.license.services.RestaurantService;

@ManagedBean
//@RequestScoped
@SessionScoped
public class HomeRestaurantsListBean {

/*	private String name;
	private String description;
	private int stars;
	private String street;
	private String number;*/
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
/*

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}*/

}