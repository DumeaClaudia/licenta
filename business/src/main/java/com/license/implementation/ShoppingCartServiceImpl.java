package com.license.implementation;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.license.Product;
import com.license.ShoppingCart;
import com.license.repositories.shoppingCart.ShoppingCartRepository;
import com.license.services.ShoppingCartService;


@Remote(ShoppingCartService.class)
@Stateless
public class ShoppingCartServiceImpl implements ShoppingCartService{
	

	@EJB
	private ShoppingCartRepository repository;
	
	public List<ShoppingCart> getShoppingCart(long idShoppingCart) {
		return repository.retrieveShoppingCartById(idShoppingCart);
	}

	public List<Long> getActiveShoppingCartForUser(long idUser) {
		return repository.retrieveActiveShoppingCartForUserId(idUser);
	}
	
	public Product addProductToShoppigCart(long idProduct) {
		return repository.getProductById(idProduct);
	}
}
