package com.license.services;

import javax.ejb.Remote;

import com.license.User;


@Remote
public interface UserService {
	User login(String username, String password);
	void register(User user);
}
