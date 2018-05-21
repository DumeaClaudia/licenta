package com.license.services;

import java.util.List;

import com.license.Product;
import com.license.ShoppingCart;

public interface ShoppingCartService {
	List<ShoppingCart>  getShoppingCart(long idShoppingCart);
	List<Long> getActiveShoppingCartForUser(long idUser);
	Product addProductToShoppigCart(long idProduct);	
}
