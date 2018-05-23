package com.license;

public class AddProductRequest {

	private long idCart;
	private long idProduct;

	public long getIdCart() {
		return idCart;
	}
	public void setIdCart(long idCart) {
		this.idCart = idCart;
	}
	public long getIdProduct() {
		// FacesContext context = FacesContext.getCurrentInstance();
		// idProduct = (long)
		// context.getExternalContext().getSessionMap().get("productId");
		return idProduct;
	}
	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}
	
	
}