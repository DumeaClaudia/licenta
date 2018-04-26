package com.license.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.license.Restaurant;
import com.license.services.RestaurantService;

@ManagedBean(name = "restaurant")
@RequestScoped
// @SessionScoped
public class RestaurantBean implements Serializable {

	private static final long serialVersionUID = -3003921679735175082L;
	private String name;
	private String description;
	private int stars;
	private String street;
	private String number;
	private List<Restaurant> restaurants; // = new ArrayList<Restaurant>();

	@EJB
	private RestaurantService restaurantService;

	@PostConstruct
	public void displayAllRestaurants() {
		restaurants = restaurantService.displayRestaurants();
		System.out.println("test");

	}

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

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
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
	}

}