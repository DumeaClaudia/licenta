package com.license.shoppingCart;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.license.Cart;
import com.license.Comment;
import com.license.Product;
import com.license.ShoppingCartProducts;
import com.license.repositories.shoppingCart.ShoppingCartRepository;

@Remote(ShoppingCartService.class)
@Stateless
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@EJB
	private ShoppingCartRepository repository;

	public List<Product> getShoppingCartProducts(long idShoppingCart) {
		return repository.retrieveShoppingCartProductsById(idShoppingCart);
	}

	public Long getActiveShoppingCartForUser(long idUser) {
		return repository.retrieveActiveShoppingCartForUserId(idUser);
	}

	public Product getProduct(long idProduct) {
		return repository.getProductById(idProduct);
	}

	public long createShoppingCartService(long idUser, long idRestaurant) {
		return repository.createShoppingCart(idUser, idRestaurant);
	}

	public long addProductToCart(long idUser, long idProduct, long idShoppingCart) {
		return repository.addProductToCart(idUser, idProduct, idShoppingCart);
	}

	public void removeProductFromCart(long idUser, long idProduct, long idShoppingCart) {
		repository.removeProductFromCart(idUser, idProduct, idShoppingCart);
	}

	public List<Long> getAllShoppingCartsForUser(long idUser) {
		return repository.retrieveAllShoppingCartForUser(idUser);
	}

	public Cart getCartById(long idCart) {
		return repository.retrieveCartById(idCart);
	}

	public int getNumberOfProducts(long idUser, long idProduct, long idShoppingCart) {
		return repository.retrieveNumberOfProducts(idUser, idProduct, idShoppingCart);
	}

	public void updateNumberOfProducts(long idUser, long idProduct, long idShoppingCart, int nrProducts) {
		repository.updateNrOfProducts(idUser, idProduct, idShoppingCart, nrProducts);
	}

	public List<ShoppingCartProducts> getCartProductsForUser(long idUser, long idCart) {
		return repository.retrieveShoppingCartProductIds(idUser, idCart);
	}

	public void removeAProductFromCurrentCart(long idUser, long idProduct, long idShoppingCart) {
		repository.removeProductFromCurrentCart(idUser, idProduct, idShoppingCart);

	}

	public long createNewCartForUser(long idUser) {
		return repository.createCartForUser(idUser);
	}

	public void updateDateForCartAfterCheckout(long idUser, long idCart) {
		repository.updateCartAfterCheckout(idUser, idCart);
	}

	public Long getCurrentCart(long idUser) {
		return repository.retrieveCurrentCartForUser(idUser);
	}

	public void addUserToCart(long idUser, long currentCartId) {
		repository.addUserToCurrentCart(idUser, currentCartId);
	}

	public void updatePreviousCartForNewUser(long idNewUser, long currentCartId) {
		repository.updateLastCartForNewUser(idNewUser, currentCartId);
	}

	public List<Comment> getAllCommentsForCart(Long currentCart) {
		return repository.retrieveAllCommentsForCart(currentCart);
	}

	public void addNewComment(long idUser, long idCart, String description, Date date) {
		repository.addComment(idUser, idCart, description, date);
	}

}
