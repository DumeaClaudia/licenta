package com.license.services;

import java.util.List;

import com.license.Product;
import com.license.ShoppingCart;

public interface ShoppingCartService {
	List<ShoppingCart>  getShoppingCart(long idShoppingCart);
	List<Long> getActiveShoppingCartForUser(long idUser);
<<<<<<< HEAD
	Product addProductToShoppigCart(long idProduct);	
=======

	Product addProductToShoppigCart(long idProduct);
>>>>>>> branch 'master' of https://github.com/DumeaClaudia/licenta.git
}
