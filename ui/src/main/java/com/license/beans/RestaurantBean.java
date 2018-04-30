package com.license.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import com.license.Product;
import com.license.Restaurant;
import com.license.services.RestaurantService;

@ManagedBean
@RequestScoped
//@SessionScoped
public class RestaurantBean {

	private Restaurant restaurant = null;
	private List<Product> products = new ArrayList<Product>(); 
	private long id;

	@EJB
	private RestaurantService restaurantService;

	public long getId() {
		return id;
	}

	public void setId(long id) {
/*		for(Restaurant r:  restaurants) {
			if(r.getId() == id) {
				restaurant = r;
			}
		}*/
		restaurant = restaurantService.getRestaurantById(id);
		products = restaurantService.getAllProductsForRestaurant(id);
		this.id = id;
	}
	

	public List<Product>  displayAllProductsForRestaurant() {
		return this.products;
	}
		

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	

}