package com.license.shoppingCart;

import java.util.List;

import com.license.Cart;
import com.license.Product;

public interface ShoppingCartService {
	Cart getCartById(long idCart);
	
	List<Product> getShoppingCartProducts(long idShoppingCart);

	List<Long> getActiveShoppingCartForUser(long idUser);
	
	List<Long> getAllShoppingCartsForUser(long idUser);

	Product getProduct(long idProduct);

	long createShoppingCartService(long idUser, long idRestaurant);

	long addProductToCart(long idUser, long idProduct, long idShoppingCart);
	
	void removeProductFromCart(long idUser, long idProduct, long idShoppingCart);
	
	int getNrCartProducts(long idUser, long idProduct, long idShoppingCart);
}
