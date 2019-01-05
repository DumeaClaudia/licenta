package com.license.user;

import java.util.List;

import javax.ejb.Remote;

import com.license.User;


@Remote
public interface UserService {
	void register(User user);
	User login(String username, String password);
	List<User> getUsers();
	//void logout(String username, String password);
}
