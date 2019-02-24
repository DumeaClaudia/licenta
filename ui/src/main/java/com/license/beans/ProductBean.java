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

	private static final long serialVersionUID = 1L;
	private long id;	
	private Product product;
	
	@EJB
	private RestaurantService restaurantService;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.product = restaurantService.getProductById(id);
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
