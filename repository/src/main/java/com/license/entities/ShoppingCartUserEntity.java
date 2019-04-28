package com.license.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name = "shopping_cart_users")
@IdClass(ShoppingCartUserEntityId.class)
@NamedQueries({
		@NamedQuery(name = "shopping_cart_users.getAllShoppingCartsForUser", 
			query = "Select sc FROM shopping_cart_users sc where sc.idUser=:idUser" ),
		@NamedQuery(name = "shopping_cart_users.getCurrentCartForUser", 
			query = "Select sc.idShoppingCart FROM shopping_cart_users sc where sc.idUser=:idUser and sc.currentCart=:isCurrentCart" ),
		@NamedQuery(name = "shopping_cart_users.getAllUsersForCart", 
			query = "Select sc.idUser FROM shopping_cart_users sc where sc.idShoppingCart=:idShoppingCart" ),
		
		
})
public class ShoppingCartUserEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @Column(name = "idUser")
	private long idUser;
	
	@Id @Column(name = "idShoppingCart")
	private long idShoppingCart;
	
	@Column(name = "currentCart")
	private boolean currentCart;

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

	public boolean isCurrentCart() {
		return currentCart;
	}

	public void setCurrentCart(boolean currentCart) {
		this.currentCart = currentCart;
	}	
	
}
