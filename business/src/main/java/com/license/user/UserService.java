package com.license.user;

import java.util.List;

import javax.ejb.Remote;

import com.license.User;

@Remote
public interface UserService {
	void register(User user);
	
	User login(String username, String password);
	
	List<User> getUsers();

	List<Long> getUsersIds(long currentCart);
	
	User getUserById(long idUser);
	
	User getUserByName(String username);
	
	List<User> getAllUsers(List<Long> activeUsers);
}
