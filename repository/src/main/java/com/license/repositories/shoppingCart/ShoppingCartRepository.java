package com.license.repositories.shoppingCart;

import java.util.List;
import com.license.Cart;
import com.license.Product;
import com.license.ShoppingCartProducts;

public interface ShoppingCartRepository {
	public Cart retrieveCartById(long idCart);

	public List<Product> retrieveShoppingCartProductsById(long id);

	public Long retrieveActiveShoppingCartForUserId(long idUser);

	public List<Long> retrieveAllShoppingCartForUser(long idUser);
	
	public Product getProductById(long idProduct);

	public long createShoppingCart(long idUser, long idRestaurant);

	public long addProductToCart(long idUser, long idProduct, long idShoppingCart);

	public void removeProductFromCart(long idUser, long idProduct, long idShoppingCart);
	
	public void removeProductFromCurrentCart(long idUser, long idProduct, long idShoppingCart);

	public int retrieveNumberOfProducts(long idUser, long idProduct, long idShoppingCart);

	public void updateNrOfProducts(long idUser, long idProduct, long idShoppingCart, int nrProducts);

	public List<ShoppingCartProducts> retrieveShoppingCartProductIds(long idUser, long idCart);
	
	public long createCartForUser(long idUser);

	public void updateCartAfterCheckout(long idUser, long idCart);
}
