package com.license;

public class AddProductRequest {

	private long idProduct;
	private long idUser;

	public long getIdProduct() {
		// FacesContext context = FacesContext.getCurrentInstance();
		// idProduct = (long)
		// context.getExternalContext().getSessionMap().get("productId");
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
	
	
}