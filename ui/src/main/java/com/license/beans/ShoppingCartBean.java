package com.license.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.license.Product;
//import com.license.ProductShoppingCart;
import com.license.ShoppingCart;
import com.license.services.ShoppingCartService;

@ManagedBean
@RequestScoped

public class ShoppingCartBean {
	private long userId;
	private long idProduct;
	private int nrProducts;
	private String restaurant;
	private String product;
	private String user;
	private List<ShoppingCart> shoppingCarts = null;
	private List<Product> products = null;
	List<Long> ids;
	
	@EJB
	private ShoppingCartService shoppingCartService;

	public void setUserId(Long userId) {
		this.userId = userId;
		
		// nu e ok...
		ids = shoppingCartService.getActiveShoppingCartForUser(userId);
		shoppingCarts = shoppingCartService.getShoppingCart(ids.get(0));
		
		products.add(shoppingCartService.getProduct(idProduct)); // ? de 2 ori
	}
	
	public void getProducts( long idProduct) {
		products.add(shoppingCartService.getProduct(idProduct));
	}

	public List<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}

	public void setShoppingCarts(List<ShoppingCart> shoppingCart) {
		this.shoppingCarts = shoppingCart;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public Integer getNrProducts() {
		return nrProducts;
	}

	public void setNrProducts(Integer nrProducts) {
		this.nrProducts = nrProducts;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Long getUserId() {
		return userId;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public ShoppingCartService getShoppingCartService() {
		return shoppingCartService;
	}

	public void setShoppingCartService(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}

	public void setNrProducts(int nrProducts) {
		this.nrProducts = nrProducts;
	}
	

}
