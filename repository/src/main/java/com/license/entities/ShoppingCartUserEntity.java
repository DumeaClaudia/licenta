package com.license.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name = "shopping_cart_users")
@IdClass(ShoppingCartUserEntityId.class)

public class ShoppingCartUserEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @Column(name = "idUser")
	private long idUser;
	
	@Id @Column(name = "idShoppingCart")
	private long idShoppingCart;

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdShoppingCart() {
		return idShoppingCart;
	}

	public void setIdShoppingCart(long idShoppingCart) {
		this.idShoppingCart = idShoppingCart;
	}
}