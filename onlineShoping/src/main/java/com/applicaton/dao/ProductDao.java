package com.applicaton.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.applicaton.model.Product;

public interface ProductDao {

	ResultSet get(int productId) throws SQLException;

	ResultSet getAll() throws SQLException;

	ResultSet getAllAdmin() throws SQLException;

	int add(Product product) throws SQLException;

	int update(Product product) throws SQLException;

	int delete(int productId) throws SQLException;
	
	int updateProduct(Product product) throws SQLException;
}
