package com.license.repositories.shoppingCart;

import java.util.List;
import com.license.Product;

public interface ShoppingCartRepository {
	
	public List<Product> retrieveShoppingCartProductsById(long id);

	public List<Long> retrieveActiveShoppingCartForUserId(long idUser);

	public Product getProductById(long idProduct);

	public long createShoppingCart(long idUser, long idRestaurant);

	public long addProductToCart(long idUser, long idProduct, long idShoppingCart);
	
	public void removeProductFromCart(long idUser, long idProduct, long idShoppingCart);
}
