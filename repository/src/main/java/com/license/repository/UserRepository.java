package com.license.repository;

import com.license.User;

public interface UserRepository {
	public String find(String username, String password);
	public void register(User user);
}

