package com.license;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	//TODO in android:
	// am de trimis id-urile, gen cand dau click.
	// de adaugat idRestaurant in restaurantDetails..sau cum era.. si in Product
	
	private long id;
	private long idRestaurant;
	private String image;
	private String name;
	private String category;
	private String description;
	private double price;
	private int discount;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(long idRestaurant) {
		this.idRestaurant = idRestaurant;
	}	
}
