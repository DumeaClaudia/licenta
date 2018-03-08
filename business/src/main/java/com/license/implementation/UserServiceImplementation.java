package com.license.implementation;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.license.User;
import com.license.repository.UserRepository;

@Stateless
public class UserServiceImplementation  {

    @EJB
	private UserRepository repository;

	public String login(String username, String password) {
		System.out.println("called login service");
		String user = repository.find(username, password);
		return user;
	}

	public void register(User user) {
		repository.register(user);
		System.out.println("User");
	}

}
