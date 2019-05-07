package com.license.shoppingCart;

import java.util.Date;
import java.util.List;

import com.license.Cart;
import com.license.Comment;
import com.license.Product;
import com.license.ShoppingCartProducts;

public interface ShoppingCartService {
	Cart getCartById(long idCart);

	List<Product> getShoppingCartProducts(long idShoppingCart);

	Long getActiveShoppingCartForUser(long idUser);

	Long getCurrentCart(long idUser);

	List<Long> getAllShoppingCartsForUser(long idUser);

	Product getProduct(long idProduct);

	List<ShoppingCartProducts> getCartProductsForUser(long idUser, long idCart);

	long createNewCartForUser(long idUser);

	long addProductToCart(long idUser, long idProduct, long idShoppingCart);

	void removeProductFromCart(long idUser, long idProduct, long idShoppingCart);

	void removeAProductFromCurrentCart(long idUser, long idProduct, long idShoppingCart);

	int getNumberOfProducts(long idUser, long idProduct, long idShoppingCart);

	void updateNumberOfProducts(long idUser, long idProduct, long idShoppingCart, int nrProducts);

	void updateDateForCartAfterCheckout(List<Long> usersIds, long idCart);

	void addUserToCart(long id, long currentCartId);

	void updatePreviousCartForNewUser(long idNewUser, long currentCartId);

	List<Comment> getAllCommentsForCart(Long currentCart);

	void addNewComment(long idUser, long idCart, String description, Date date);

}
