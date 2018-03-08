package com.license.services;

import com.license.User;

public interface UserService {
	String login(String username, String password);

	void register(User user);
}
