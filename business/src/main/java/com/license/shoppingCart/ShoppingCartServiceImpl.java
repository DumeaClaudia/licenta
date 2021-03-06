package com.license.shoppingCart;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.license.Cart;
import com.license.Comment;
import com.license.DeliveryData;
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

	public Product getProduct(long idProduct) {
		return repository.getProductById(idProduct);
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

	public void updateDateForCartAfterCheckout(List<Long> usersIds, long idCart) {
		repository.updateCartAfterCheckout(usersIds, idCart);
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

	public void addDeliveryData(DeliveryData deliveryDetails) {
		repository.addDeliveryDetails(deliveryDetails);
	}

	public DeliveryData getDeliveryDataByIdCart(long idCart) {
		return repository.getDeliveryDetailsByIdCart(idCart);
	}

	public void deleteFromTablesOldCart(Long currentCart, Long userId) {
		repository.deleteOldCart(currentCart, userId);
	}
	
	public void updateNewCart(long cartId, long userId) {
		repository.updateTheNewCart(cartId, userId);
	}

}
