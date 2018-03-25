package com.license.services;

import com.license.User;
import javax.ejb.Remote;


@Remote
public interface UserService {
	String login(String username, String password);
	void register(User user);
}
