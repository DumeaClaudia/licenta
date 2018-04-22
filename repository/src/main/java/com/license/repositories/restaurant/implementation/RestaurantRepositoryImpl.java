package com.license.repositories.restaurant.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.license.Restaurant;
import com.license.entities.RestaurantEntity;
import com.license.repositories.restaurant.RestaurantRepository;

@Stateless
@Remote(RestaurantRepository.class)

public class RestaurantRepositoryImpl implements RestaurantRepository {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	private EntityManager em = emf.createEntityManager();

	public List<Restaurant> display() {
	
		System.out.println("in display method repository");

		List<RestaurantEntity> restaurants = new ArrayList<RestaurantEntity>();
		List<Restaurant> restaurantsResponse = new ArrayList<Restaurant>();
		
		Query query = em.createNamedQuery("restaurant.display");

		restaurants = (List<RestaurantEntity>) query.getResultList();
		
		if (restaurants == null) {
			System.out.println("se pare ca nu au fost gasite restaurante in baza de date ha ha ha");
			// return"";
		}
	
		for(RestaurantEntity r: restaurants) {
			Restaurant res = new Restaurant();
		
			res.setName(r.getName());
			res.setDescription(r.getDescription());
			res.setStars(r.getStars());
			res.setStreet(r.getStreet());
			
			restaurantsResponse.add(res);
		}
		
		return restaurantsResponse;

	}
	//
	// public void register(User user) {
	// // System.out.println("in register method");
	//
	// UserEntity entity = new UserEntity();
	//
	// entity.setFirstName(user.getFirstName());
	// entity.setLastName(user.getLastName());
	// entity.setUsername(user.getUsername());
	// entity.setPassword(user.getPassword());
	// entity.setEmail(user.getEmail());
	//
	// em.getTransaction().begin();
	// em.persist(entity);
	// em.getTransaction().commit();
	// }

}
