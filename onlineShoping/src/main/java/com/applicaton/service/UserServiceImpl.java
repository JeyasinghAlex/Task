package com.applicaton.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.applicaton.dao.UserDao;
import com.applicaton.dao.UserDaoImpl;
import com.applicaton.model.User;

public class UserServiceImpl implements UserService {

	private static UserServiceImpl instance;

	private UserServiceImpl() {

	}

	public static UserServiceImpl getInstance() {
		if (instance == null) {
			instance = new UserServiceImpl();
		}
		return instance;
	}

	@Override
	public boolean registration(User user) {
		UserDao dao = UserDaoImpl.getInstance();
		int id = 0;
		try {
			id = dao.insertUser(user);
			if (id != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int isUserAvailable(String email, String password) {
		int userId = 0;
		UserDao dao = UserDaoImpl.getInstance();
		try {
			ResultSet rs = dao.getUser(email, password);
			if (rs.next()) {
				userId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}

	@Override
	public boolean saveUser(User user) {
		UserDao dao = UserDaoImpl.getInstance();
		int isSave = 0;
		try {
			isSave = dao.insertUser(user);
			if (isSave != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User findUserByEmail(String email) {
		UserDao dao = UserDaoImpl.getInstance();
		User user = new User();
		try {
			ResultSet rs = dao.findUserByMail(email);
			if (rs.next()) {
				user.setId(rs.getInt(1));
				user.setEmail(rs.getString(2));
				user.setPassword(rs.getString(3));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
