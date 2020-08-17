package com.applicaton.service;

import com.applicaton.model.User;

public interface UserService {

	boolean saveUser(User user);

	User findUserByEmail(String email);
	
	 boolean registration(User user);
}
