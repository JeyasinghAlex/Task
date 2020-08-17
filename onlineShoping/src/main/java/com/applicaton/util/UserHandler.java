package com.applicaton.util;

import com.applicaton.model.User;

public class UserHandler {

	private static final ThreadLocal<User> context = new ThreadLocal<>();

	public static void setUser(User user) {
		 context.set(user);
	}
	
	public static User getUser() {
		 return context.get();
	}
	
	public static void removeUser() {
		context.remove();
	}

}