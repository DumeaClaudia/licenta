package com.license.services;

import java.util.Map;

import javax.ejb.Remote;

import com.license.User;


@Remote
public interface UserService {
	Map<String, String> login(String username, String password);
	void register(User user);
}
