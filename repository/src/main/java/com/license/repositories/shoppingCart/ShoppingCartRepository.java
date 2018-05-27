package com.license.repositories.shoppingCart;

import java.util.List;

import com.license.Product;
import com.license.ShoppingCart;

public interface ShoppingCartRepository {
	public List<ShoppingCart> retrieveShoppingCartById(long id);

	public List<Long> retrieveActiveShoppingCartForUserId(long idUser);

	public Product getProductById(long idProduct);

	public long createShoppingCart(long idUser, long idRestaurant);

	public long addProductToCart(long idUser, long idProduct, long idShoppingCart);

}
