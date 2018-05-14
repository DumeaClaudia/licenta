package com.license.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name = "product")
@NamedQueries({
		@NamedQuery(name = "product.getAllProducts", query = "Select p FROM product p where p.idRestaurant=:idRestaurant"),
		@NamedQuery(name = "product.getProductsByCategory", query = "Select p FROM product p where p.idRestaurant=:idRestaurant and p.category=:category"),
		@NamedQuery(name = "product.getProductById", query = "Select p FROM product p where p.id=:iProduct")})
public class ProductEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "idRestaurant")
	private long idRestaurant;
	
	@Column(name = "category")
	private String category;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private double price;

	@Column(name = "discount")
	private int discount;

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
