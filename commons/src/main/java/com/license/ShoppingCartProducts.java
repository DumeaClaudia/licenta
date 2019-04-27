package com.license;

import java.io.Serializable;

public class ShoppingCartProducts implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5614911322935237185L;
	private long idProduct;
	private long idUser;
	private long idShoppingCart;
	private int nrProducts;

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
	
	public long getIdShoppingCart() {
		return idShoppingCart;
	}
	public void setIdShoppingCart(long idShoppingCart) {
		this.idShoppingCart = idShoppingCart;
	}
	public int getNrProducts() {
		return nrProducts;
	}
	public void setNrProducts(int nrProducts) {
		this.nrProducts = nrProducts;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idProduct ^ (idProduct >>> 32));
		result = prime * result + (int) (idShoppingCart ^ (idShoppingCart >>> 32));
		result = prime * result + (int) (idUser ^ (idUser >>> 32));
		result = prime * result + nrProducts;
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
		ShoppingCartProducts other = (ShoppingCartProducts) obj;
		if (idProduct != other.idProduct)
			return false;
		if (idShoppingCart != other.idShoppingCart)
			return false;
		if (idUser != other.idUser)
			return false;
		if (nrProducts != other.nrProducts)
			return false;
		return true;
	}
	
}
