package com.license.data;

public class CartSummaryItem {
	
	long idCart;
	boolean cartActive;
	String createdDate;
    String cartDescription;
    
	public CartSummaryItem(long idCart, boolean cartActive, String createdDate, String cartDescription) {		
		this.idCart = idCart;
		this.cartActive = cartActive;
		this.createdDate = createdDate;
		this.cartDescription = cartDescription;
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
}
