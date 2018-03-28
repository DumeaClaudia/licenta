package com.license.repository;

import java.util.Map;

import com.license.User;

public interface UserRepository {
	public Map<String, String> find(String username, String password);
	public void register(User user);
}

