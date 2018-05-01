package com.license.repositories.shoppingCart.implementation;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.license.Restaurant;
import com.license.ShoppingCart;
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
	
	
	public ShoppingCart retrieveShoppingCartById(long id) {
		
		ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
		ShoppingCart shoppingCartResponse = new ShoppingCart();
		
		Query query = em.createNamedQuery("shoppingCartProducts.getNativeShoppingCarInfo");
		//query.setParameter("id", id);
		query.setParameter(1, id);
		List<NativeShoppingCartEntity> shoppingCartProducts = (List<NativeShoppingCartEntity>) query.getResultList();
		
		
		if (shoppingCartProducts == null) {
			System.out.println("se pare ca nu au fost gasite restaurante in baza de date ha ha ha");
			// return "";
		}
		
		shoppingCartResponse.setId(restaurant.getId());
		shoppingCartResponse.setName(restaurant.getName());
		shoppingCartResponse.setDescription(restaurant.getDescription());
		//restaurantResponse.setProducts(restaurant.);
		restaurantResponse.setAddress(restaurant.getAddress());
		restaurantResponse.setStars(restaurant.getStars());
		restaurantResponse.setImage(restaurant.getImage());
		
		return restaurantResponse;
		
		//return null;
	}
}
