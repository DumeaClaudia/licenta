package com.license.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name = "shopping_cart_products")
@NamedQueries({
		@NamedQuery(name = "shopping_cart_products.getProductsFromCart", 
			query = "Select p FROM shopping_cart_products p where p.idShoppingCart = :idShoppingCart"),
		
		@NamedQuery(name = "shopping_cart_products.deleteProductFromCart",
			query = "select p FROM shopping_cart_products p where p.idUser=:idUser and p.idProduct = :idProduct and p.idShoppingCart = :idShoppingCart"),
		
		@NamedQuery(name = "shopping_cart_products.getNrOfProducts",
			query = "select p.nrProducts FROM shopping_cart_products p where p.idUser=:idUser and p.idProduct = :idProduct and p.idShoppingCart = :idShoppingCart"),
		
		@NamedQuery(name = "shopping_cart_products.getCartProductsForUser",
		query = "select p FROM shopping_cart_products p where p.idUser=:idUser and p.idShoppingCart = :idShoppingCart")
		
})

public class ShoppingCartProductsEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
