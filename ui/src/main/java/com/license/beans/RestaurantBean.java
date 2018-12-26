package com.license.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.AjaxBehaviorEvent;

import com.license.Product;
import com.license.Restaurant;
import com.license.restaurant.RestaurantService;

@ManagedBean
@RequestScoped
// @SessionScoped
public class RestaurantBean implements Serializable {

	private Restaurant restaurant = null;
	private List<Product> products = new ArrayList<Product>();
	private long id;
	private long productId;
	private List<String> categories;
	Product product = new Product();
	private int totalStars = 5;
	private double step = 1;
	private boolean disabled = false;
	private Double rating1 = new Double(2.2);

	@EJB
	private RestaurantService restaurantService;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		/*
		 * for(Restaurant r: restaurants) { if(r.getId() == id) { restaurant = r; } }
		 */
		restaurant = restaurantService.getRestaurantById(id);
		products = restaurantService.getAllProductsForRestaurant(id);
		categories = restaurantService.getCategoriesOfProducts(id);
		this.id = id;
	}

	public List<Product> displayAllProductsForRestaurant() {
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

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public int getTotalStars() {
		return totalStars;
	}

	public void setTotalStars(int totalStars) {
		this.totalStars = totalStars;
	}

	public double getStep() {
		return step;
	}

	public void setStep(double step) {
		this.step = step;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Double getRating1() {
		return rating1;
	}

	public void setRating1(Double rating1) {
		this.rating1 = rating1;
	}

	public void rate(AjaxBehaviorEvent actionEvent) {
		//String score = ((UIOutput) actionEvent.getSource()).getValue();
		
		UIComponent component = (UIComponent) actionEvent.getSource();
		String attributeName = (String) component.findComponent("name").getAttributes().get("value");
		Double attributeValue = (Double) component.findComponent("value").getAttributes().get("value");
		
		Double score = attributeValue;
		this.rating1 = score;
		System.out.println("Rate ajax action executed! Score: " + score);
	}

}