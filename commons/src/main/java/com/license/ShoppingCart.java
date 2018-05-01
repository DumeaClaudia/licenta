package com.license;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private long id;
	private double totalPrice;
	private Date createdDate;
	private Date sendDate;
	private int nrProducts;
	private Restaurant restaurant;
	private List<Product> products;
	private User user;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public int getNrProducts() {
		return nrProducts;
	}
	public void setNrProducts(int nrProducts) {
		this.nrProducts = nrProducts;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
