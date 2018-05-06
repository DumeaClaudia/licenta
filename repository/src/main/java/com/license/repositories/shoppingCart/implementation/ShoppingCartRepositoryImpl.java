package com.license.repositories.shoppingCart.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.event.ListSelectionEvent;

import com.license.Restaurant;
import com.license.ShoppingCart;
import com.license.entities.CartForUserEntity;
import com.license.entities.NativeShoppingCartEntity;
import com.license.entities.RestaurantEntity;
import com.license.entities.ShoppingCartEntity;
import com.license.repositories.restaurant.RestaurantRepository;
import com.license.repositories.shoppingCart.ShoppingCartRepository;

@Stateless
@Remote(ShoppingCartRepository.class)

public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	private EntityManager em = emf.createEntityManager();

	public List<ShoppingCart> retrieveShoppingCartById(long id) {

		List<ShoppingCart> scList = new ArrayList<>();
		Query query = em.createNamedQuery("shoppingCartProducts.getNativeShoppingCartInfo");
		query.setParameter(1, id);

		List<NativeShoppingCartEntity> qResult = query.getResultList();

		if (qResult == null) {
			System.out.println("se pare ca nu au fost gasite sc in baza de date ha ha ha");
		}
		for (NativeShoppingCartEntity e : qResult) {
			ShoppingCart shoppingCart = new ShoppingCart();

			shoppingCart.setId(e.getId());
			shoppingCart.setProduct(e.getProduct());
			shoppingCart.setUser(e.getUser());
			shoppingCart.setRestaurant(e.getRestaurant());
			shoppingCart.setNrProducts(e.getNrProducts());
			
			scList.add(shoppingCart);
		}
		return scList;
	}

	public List<Long> retrieveActiveShoppingCartForUserId(long idUser) {

		List<Long> idSc = new ArrayList<Long>();

		Query query = em.createNamedQuery("shoppingCartProducts.getNativeShoppingCartForUser");
		query.setParameter(1, idUser);

		List<CartForUserEntity> shoppingCartIdUsers =  query.getResultList();

		if (shoppingCartIdUsers == null) {
			System.out.println("se pare ca nu au fost gasite sc in baza de date ha ha ha");
		}

		for (CartForUserEntity e : shoppingCartIdUsers) {
			idSc.add(e.getIdShoppingCart());
		}

		return idSc;
	}
}
