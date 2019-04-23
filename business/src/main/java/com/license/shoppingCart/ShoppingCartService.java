package com.license.shoppingCart;

import java.util.List;

import com.license.Cart;
import com.license.Product;
import com.license.ShoppingCartProducts;

public interface ShoppingCartService {
	Cart getCartById(long idCart);

	List<Product> getShoppingCartProducts(long idShoppingCart);

	Long getActiveShoppingCartForUser(long idUser);

	List<Long> getAllShoppingCartsForUser(long idUser);

	Product getProduct(long idProduct);

	List<ShoppingCartProducts> getCartProductsForUser(long idUser, long idCart);

	long createShoppingCartService(long idUser, long idRestaurant);

	long createNewCartForUser(long idUser);

	long addProductToCart(long idUser, long idProduct, long idShoppingCart);

	void removeProductFromCart(long idUser, long idProduct, long idShoppingCart);

	void removeAProductFromCurrentCart(long idUser, long idProduct, long idShoppingCart);

	int getNumberOfProducts(long idUser, long idProduct, long idShoppingCart);

	void updateNumberOfProducts(long idUser, long idProduct, long idShoppingCart, int nrProducts);

	void updateDateForCartAfterCheckout(long idUser, long idCart);
}
