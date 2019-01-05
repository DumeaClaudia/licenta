package com.license.repositories.user;

import java.util.List;

import com.license.User;

public interface UserRepository {
	public User find(String username, String password);
	public void register(User user);
	public List<User> retrieveUsers();
}

