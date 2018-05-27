package com.license.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name = "shopping_cart_users")
@IdClass(ShoppingCartUserEntityId.class)

public class ShoppingCartUserEntity {
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
