package com.license.services;

import java.util.List;

import com.license.Product;

public interface ShoppingCartService {
	List<Product> getShoppingCartProducts(long idShoppingCart);

	List<Long> getActiveShoppingCartForUser(long idUser);

	Product getProduct(long idProduct);

	long createShoppingCartService(long idUser, long idRestaurant);

	long addProductToCart(long idUser, long idProduct, long idShoppingCart);
	
	void removeProductFromCart(long idUser, long idProduct, long idShoppingCart);
}
