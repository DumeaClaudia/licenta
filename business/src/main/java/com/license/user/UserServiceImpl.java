package com.license.user;

import java.util.List;

import javax.ejb.EJB;
//import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.license.User;
import com.license.repositories.user.UserRepository;

//@Remote(UserService.class)
@Stateless
public class UserServiceImpl implements UserService {

	@EJB
	private UserRepository repository;
	
	public void register(User user) {
		repository.register(user);
	}
	
	public User login(String username, String password) {
		return repository.find(username, password);
	}
	
	public List<User> getUsers() {
		return repository.retrieveUsers();
	}
	
	public List<Long> getUsersIds(long currentCart) {
		return repository.retrieveUsers(currentCart);
	}
	
	public User getUserById(long idUser) {
		return repository.retrieveUserById(idUser);
	}

	public List<User> getAllUsers(List<Long> activeUsers) {
		return repository.retrieveRemainingUsers(activeUsers);
	}
	public 	User getUserByName(String username) {
		return repository.retrieveUserByName(username);
	}
}
