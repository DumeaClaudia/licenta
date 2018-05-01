package com.license.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
/*
@Entity(name = "shoppingCartProducts")
@NamedQueries({
		@NamedQuery(name = "shoppingCartProducts.getShoppingCarInfo", 
				query = "select u.username, r.name, p.name, p.description  FROM shoppingCartProducts scp \n" + 
				"	join shoppingCart sc on scp.idShoppingCart=sc.id \n" + 
				"	join restaurant r on sc.idRestaurant=r.id\n" + 
				"	join user u on scp.idUser = u.id \n" + 
				"	join product p on scp.idProduct=p.id\n" + 
				"	where sc.id=:id") }) */

public class ShoppingCartProductsEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nrProducts")
	private int nrProducts;

	@Column(name = "idRestaurant")
	private long idRestaurant;
	
	@Column(name = "idProduct")
	private long idProduct;
	
	@Column(name = "idUser")
	private long idUser;

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

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
}
