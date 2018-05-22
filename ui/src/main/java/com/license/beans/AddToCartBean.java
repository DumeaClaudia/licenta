package com.license.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class AddToCartBean {
	Long productId;

	public Long getProductId() {
		FacesContext context = FacesContext.getCurrentInstance();
		productId = (Long) context.getExternalContext().getSessionMap().get("productId");
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
