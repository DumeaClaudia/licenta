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
import com.license.ShoppingCart;
import com.license.services.RestaurantService;
import com.license.services.ShoppingCartService;

@ManagedBean
@RequestScoped

public class ShoppingCartBean {
	private Restaurant restaurant = null;
	private List<Product> products = new ArrayList<Product>(); 
	private long id;
	private ShoppingCart shoppingCart=null;
	

	@EJB
	private ShoppingCartService shoppingCartService;

	public long getId() {
		return id;
	}
	//getShoppingCart
	public void setId(long id) {
	
//		restaurant = restaurantService.getRestaurantById(id);
//		products = restaurantService.getAllProductsForRestaurant(id);
//		
		shoppingCart= shoppingCartService.getShoppingCart(id);
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
