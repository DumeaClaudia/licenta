package com.license;

import java.io.Serializable;
import java.util.List;

public class ProductShoppingCart implements Serializable{
	
	private static final long serialVersionUID = -2868295448461555839L;
	List<Product> products;
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
