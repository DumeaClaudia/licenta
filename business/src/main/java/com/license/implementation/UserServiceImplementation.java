package com.license.implementation;

import javax.ejb.EJB;
//import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.license.User;
import com.license.repository.UserRepository;
import com.license.services.UserService;

//@Remote(UserService.class)
@Stateless
public class UserServiceImplementation implements UserService {

	@EJB
	private UserRepository repository;
	
	
	public void register(User user) {
		//System.out.println("am ajuns in metoda register() din UserServiceImplementation...");
		repository.register(user);
		//System.out.println("User");
	}
	
	public User login(String username, String password) {
		//System.out.println("called login service");
		User user = new User();
		user = repository.find(username, password);
		//System.out.println("after find() method");
	
		return user;
	}
}
