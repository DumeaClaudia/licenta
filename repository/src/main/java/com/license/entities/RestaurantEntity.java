package com.license.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class RestaurantEntity {
	@Id
	@Column(name = "idRestaurant")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idRestaurant;

	@Column(name = "name")
	private String name;

	@Column(name = "idAddress")
	private long idAddress;

	@Column(name = "idProduct")
	private long idProduct;

	@Column(name = "stars")
	private long stars;

	@Column(name = "idComment")
	private long idComment;

	public long getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(long idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(long idAddress) {
		this.idAddress = idAddress;
	}

	public long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}

	public long getStars() {
		return stars;
	}

	public void setStars(long stars) {
		this.stars = stars;
	}

	public long getIdComment() {
		return idComment;
	}

	public void setIdComment(long idComment) {
		this.idComment = idComment;
	}

}
