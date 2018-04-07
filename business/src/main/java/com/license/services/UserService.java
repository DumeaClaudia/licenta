package com.license.services;

import javax.ejb.Remote;

import com.license.User;


@Remote
public interface UserService {
	void register(User user);
	User login(String username, String password);
	//void logout(String username, String password);
}
