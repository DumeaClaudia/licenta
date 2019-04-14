package com.license;

public class AddProductRequest {
	private long idProduct;

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