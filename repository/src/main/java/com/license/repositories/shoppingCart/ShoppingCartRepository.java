package com.license.repositories.shoppingCart;

import com.license.Restaurant;
import com.license.ShoppingCart;

public interface ShoppingCartRepository {
	public ShoppingCart retrieveShoppingCartById(long id);
}
