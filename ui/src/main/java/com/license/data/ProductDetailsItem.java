package com.license.data;

public class ProductDetailsItem {
	private long idRestaurant;
	private long idProduct;
	private String image;
	private String name;
	private String category;
	private String description;
	private double price;
	private int discount;
	private int nrProducts;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public long getIdRestaurant() {
		return idRestaurant;
	}
	public void setIdRestaurant(long idRestaurant) {
		this.idRestaurant = idRestaurant;
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
}
