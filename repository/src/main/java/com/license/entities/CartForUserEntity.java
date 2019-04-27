package com.license.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity(name = "CartForUserEntity")
@NamedNativeQuery(name = "shoppingCartProducts.getNativeShoppingCartForUser", query = "select sc.id as idShoppingCart from shopping_cart sc join shopping_cart_users scu on"
		+ " sc.id = scu.idShoppingCart where sc.sendDate is null and scu.idUser=?1 order by sc.createdDate desc", resultClass = CartForUserEntity.class)
public class CartForUserEntity implements Serializable {
	/**
	 * 
	 */
	@Id
	@Column(name = "idShoppingCart")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idShoppingCart;

	/*
	 * @Column(name = "idUser") Long idUser;
	 * 
	 * public Long getIdUser() { return idUser; }
	 * 
	 * public void setIdUser(Long idUser) { this.idUser = idUser; }
	 */

	public Long getIdShoppingCart() {
		return idShoppingCart;
	}

	public void setIdShoppingCart(Long idShoppingCart) {
		this.idShoppingCart = idShoppingCart;
	}

}
