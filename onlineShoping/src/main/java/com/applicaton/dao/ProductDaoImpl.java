package com.applicaton.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.applicaton.model.Product;
import com.applicaton.util.ConfigUtil;

public class ProductDaoImpl implements ProductDao {

	private static ProductDaoImpl instance;

	private ProductDaoImpl() {

	}

	public static ProductDaoImpl getInstance() {
		if (instance == null) {
			instance = new ProductDaoImpl();
		}
		return instance;
	}

	@Override
	public ResultSet get(int productId) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.get_product");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setInt(1, productId);
		return pstmt.executeQuery();
	}

	@Override
	public ResultSet getAllAdmin() throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.get_products_admin");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		return pstmt.executeQuery();
	}

	@Override
	public ResultSet getAll() throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.get_products_user");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		return pstmt.executeQuery();
	}

	@Override
	public int add(Product product) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.insert_product");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setString(1, product.getName());
		pstmt.setString(2, product.getDescription());
		pstmt.setInt(3, product.getPrice());
		pstmt.setString(4, product.getBrand());
		pstmt.setInt(5, product.getQuantity());
		return pstmt.executeUpdate();
	}

	@Override
	public int update(Product product) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.update_product");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		System.out.println("i am product total ->" + product.getQuantity());
		pstmt.setInt(1, product.getQuantity());
		pstmt.setInt(2, product.getId());
		return pstmt.executeUpdate();
	}

	@Override
	public int updateProduct(Product product) throws SQLException {
		int quantity = 0;
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.update_product");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		ResultSet rs = get(product.getId());
		if (rs.next()) {
			quantity = rs.getInt(6);
		}
		pstmt.setInt(1, quantity + product.getQuantity());
		pstmt.setInt(2, product.getId());
		return pstmt.executeUpdate();
	}

	@Override
	public int delete(int productId) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.delete_product");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setInt(1, productId);
		return pstmt.executeUpdate();
	}
}
