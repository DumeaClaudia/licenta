package com.license.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable

public class ShoppingCartUserEntityId implements Serializable{

	private static final long serialVersionUID = -333524621389646714L;
	
	long idUser;
	long idShoppingCart;
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idShoppingCart ^ (idShoppingCart >>> 32));
		result = prime * result + (int) (idUser ^ (idUser >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCartUserEntityId other = (ShoppingCartUserEntityId) obj;
		if (idShoppingCart != other.idShoppingCart)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	
	
}