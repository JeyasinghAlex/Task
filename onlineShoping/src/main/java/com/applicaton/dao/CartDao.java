package com.applicaton.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.applicaton.model.Product;

public interface CartDao {

	ResultSet get(int userId, int productId) throws SQLException;

	ResultSet getAll(int userId) throws SQLException;

	int add(int userId, Product product) throws SQLException;

	int update(int userId, Product product) throws SQLException;

	int delete(int userId, Product product) throws SQLException;

}
