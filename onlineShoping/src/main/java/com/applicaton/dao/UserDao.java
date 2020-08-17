package com.applicaton.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.applicaton.model.User;

public interface UserDao {

	int insertUser(User user) throws SQLException;
	ResultSet getUser(String email, String password) throws SQLException ;
	ResultSet findUserByMail(String mail) throws SQLException;
}
