package com.license.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity(name = "shopping_cart_products")

public class ShoppingCartProductsEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nrProducts")
	private int nrProducts;

	@Column(name = "idUser")
	private long idUser;
	
	@Column(name = "idProduct")
	private long idProduct;
	
	@Column(name = "idShoppingCart")
	private long idShoppingCart;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNrProducts() {
		return nrProducts;
	}

	public void setNrProducts(int nrProducts) {
		this.nrProducts = nrProducts;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}

	public long getIdShoppingCart() {
		return idShoppingCart;
	}

	public void setIdShoppingCart(long idShoppingCart) {
		this.idShoppingCart = idShoppingCart;
	}
	
	
}
