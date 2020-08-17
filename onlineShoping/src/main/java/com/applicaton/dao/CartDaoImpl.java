package com.applicaton.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.applicaton.model.Product;
import com.applicaton.util.ConfigUtil;

public class CartDaoImpl implements CartDao {

	public static CartDaoImpl instance;

	private CartDaoImpl() {

	}

	public static CartDaoImpl getInstance() {
		if (instance == null) {
			instance = new CartDaoImpl();
		}
		return instance;
	}

	@Override
	public ResultSet get(int userId, int productId) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.get_cart_product");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, productId);
		return pstmt.executeQuery();
	}

	@Override
	public ResultSet getAll(int studentId) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.get_cart");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setInt(1, studentId);
		return pstmt.executeQuery();
	}

	@Override
	public int add(int userId, Product prod) throws SQLException {
		System.out.println("i am add product");
		int cartId = getCartId(userId);
		Product product = new Product();
		product.setId(prod.getId());
		product.setQuantity(prod.getQuantity());
		Properties properties = ConfigUtil.loadProperty();
		ResultSet rs = get(cartId, product.getId());
		if (rs.next()) {
			int quantity = rs.getInt(3) + prod.getQuantity();
			product.setQuantity(quantity);
			return update(userId, product);
		}
		String query = properties.getProperty("psql.query.insert_cart");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setInt(1, cartId);
		pstmt.setInt(2, product.getId());
		pstmt.setInt(3, product.getQuantity());
		return pstmt.executeUpdate();
	}

	private int getCartId(int userId) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.get_cart_id");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setInt(1, userId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public int update(int userId, Product product) throws SQLException {
		System.out.println("i am updated dao");
		int cartId = getCartId(userId);
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.update_cart");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setInt(1, product.getQuantity());
		pstmt.setInt(2, cartId);
		pstmt.setInt(3, product.getId());
		return pstmt.executeUpdate();
	}

	@Override
	public int delete(int userId, Product product) throws SQLException {
		int cartId = getCartId(userId);
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.remove_product_cart");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setInt(1, cartId);
		pstmt.setInt(2, product.getId());
		return pstmt.executeUpdate();
	}

}
