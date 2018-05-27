package com.license.services;

import java.util.List;

import com.license.Product;
import com.license.ShoppingCart;

public interface ShoppingCartService {
	List<ShoppingCart> getShoppingCart(long idShoppingCart);

	List<Long> getActiveShoppingCartForUser(long idUser);

	Product getProduct(long idProduct);

	long createShoppingCartService(long idUser, long idRestaurant);

	long addProductToCart(long idUser, long idProduct, long idShoppingCart);

}
