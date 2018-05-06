package com.license;

import java.io.Serializable;

public class ShoppingCart implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long id;
	private long idProduct;
	private int nrProducts;
	private String restaurant;
	private String product;
	private String user;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}
	public int getNrProducts() {
		return nrProducts;
	}
	public void setNrProducts(int nrProducts) {
		this.nrProducts = nrProducts;
	}
	public String getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
}
