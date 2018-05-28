package com.license.implementation;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.license.Product;
import com.license.repositories.shoppingCart.ShoppingCartRepository;
import com.license.services.ShoppingCartService;

@Remote(ShoppingCartService.class)
@Stateless
public class ShoppingCartServiceImpl implements ShoppingCartService{

	@EJB
	private ShoppingCartRepository repository;
	
	public List<Product> getShoppingCartProducts(long idShoppingCart) {
		return repository.retrieveShoppingCartProductsById(idShoppingCart);
	}

	public List<Long> getActiveShoppingCartForUser(long idUser) {
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
}
