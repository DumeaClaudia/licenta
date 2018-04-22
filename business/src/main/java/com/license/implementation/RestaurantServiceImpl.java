package com.license.implementation;

import javax.ejb.EJB;
//import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.license.Restaurant;
import com.license.User;
import com.license.repositories.restaurant.RestaurantRepository;
import com.license.repositories.user.UserRepository;
import com.license.services.RestaurantService;
import com.license.services.UserService;

//@Remote(UserService.class)
@Stateless
public class RestaurantServiceImpl implements RestaurantService {

	@EJB
	private RestaurantRepository repository;
	
	
//	public void shown(String restaurantName) {
//		System.out.println("am ajuns in metoda register() din RestaurantServiceImplementation...");
//		repository.display(restaurantName);
//		System.out.println("R");
//	}
	
//	public User login(String username, String password) {
//		System.out.println("called login service");
//		User user = new User();
//		user = repository.find(username, password);
//		System.out.println("after find() method");
//	
//		return user;
//	}
	
}
