package com.license.implementation;

import java.util.HashMap;
import java.util.Map;

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

	public Map<String, String> login(String username, String password) {
		System.out.println("called login service");
		Map<String, String> user = new HashMap<String, String>();
		user = repository.find(username, password);
		System.out.println("after find() method");
		return user;
	}

	public void register(User user) {
		System.out.println("am ajuns in metoda register() din UserServiceImplementation...");
		repository.register(user);
		System.out.println("User");
	}

}
