package com.license.beans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.license.Product;
import com.license.restaurant.RestaurantService;
@ManagedBean
@RequestScoped
public class ProductBean implements Serializable{

	@EJB
	private RestaurantService restaurantService;

	private static final long serialVersionUID = 1L;
	private long idProduct=1;	
	private Product product;
	
	
	public long getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(long idProduct) {
		this.product = restaurantService.getProductById(this.idProduct);
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
