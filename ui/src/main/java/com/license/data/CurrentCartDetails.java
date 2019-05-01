package com.license.data;

public class CurrentCartDetails {

	private long idProduct;
	private long idShoppingCart;
	private String nameProduct;
	private String nameRestaurant;
	private int nrProducts;
	private double price;

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

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getNameRestaurant() {
		return nameRestaurant;
	}

	public void setNameRestaurant(String nameRestaurant) {
		this.nameRestaurant = nameRestaurant;
	}

	public int getNrProducts() {
		return nrProducts;
	}

	public void setNrProducts(int nrProducts) {
		this.nrProducts = nrProducts;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CurrentCartDetails [idProduct=" + idProduct + ", idShoppingCart=" + idShoppingCart + ", nameProduct="
				+ nameProduct + ", nameRestaurant=" + nameRestaurant + ",\n NR_PRODUCTS=" + nrProducts + "\n, price=" + price
				+ ", getIdProduct()=" + getIdProduct() + ", getIdShoppingCart()=" + getIdShoppingCart()
				+ ", getNameProduct()=" + getNameProduct() + ", getNameRestaurant()=" + getNameRestaurant()
				+ ", getNrProducts()=" + getNrProducts() + ", getPrice()=" + getPrice() + ", toString()=" + super.toString() + "]";
	}
	
	
}
