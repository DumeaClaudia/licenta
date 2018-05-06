package com.license.repositories.shoppingCart;

import java.util.List;

import com.license.ShoppingCart;

public interface ShoppingCartRepository {
	public List<ShoppingCart>  retrieveShoppingCartById(long id);

	public List<Long> retrieveActiveShoppingCartForUserId(long idUser);
}
