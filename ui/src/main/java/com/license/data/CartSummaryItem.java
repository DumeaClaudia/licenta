package com.license.data;

public class CartSummaryItem {
	
	long idCart;
	boolean cartActive;
	String createdDate;
    String cartDescription;
    String nrProducts;
    String imageRestaurant;
    
	public CartSummaryItem(long idCart, boolean cartActive, String createdDate, String cartDescription, String nrProducts,
			String imageRestaurant) {		
		this.idCart = idCart;
		this.cartActive = cartActive;
		this.createdDate = createdDate;
		this.cartDescription = cartDescription;
		this.nrProducts = nrProducts;
		this.imageRestaurant = imageRestaurant;
		
	}
	
	public boolean getCartActive() {
		return cartActive;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getCartDescription() {
		return cartDescription;
	}

	public long getIdCart() {
		return idCart;
	}

	public String getNrProducts() {
		return nrProducts;
	}

	public void setNrProducts(String nrProducts) {
		this.nrProducts = nrProducts;
	}

	public String getImageRestaurant() {
		return imageRestaurant;
	}

	public void setImageRestaurant(String imageRestaurant) {
		this.imageRestaurant = imageRestaurant;
	}

	public void setIdCart(long idCart) {
		this.idCart = idCart;
	}

	public void setCartActive(boolean cartActive) {
		this.cartActive = cartActive;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setCartDescription(String cartDescription) {
		this.cartDescription = cartDescription;
	}
	
	
}
