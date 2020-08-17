package com.applicaton.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.applicaton.model.User;
import com.applicaton.util.ConfigUtil;

public class UserDaoImpl implements UserDao {

	private static UserDaoImpl instance;

	private UserDaoImpl() {

	}

	public static UserDaoImpl getInstance() {
		if (instance == null) {
			instance = new UserDaoImpl();
		}
		return instance;
	}

	@Override
	public int insertUser(User user) throws SQLException {
		int userId = 0;
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.insert_user");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, user.getName());
		pstmt.setString(2, user.getEmail());
		pstmt.setString(3, user.getContactNumber());
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
			userId = rs.getInt(1);
		}
		insertLoginDetails(userId, user);
		addUserCart(userId);
		return userId;
	}

	private int addUserCart(int userId) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.insert_user_to_cart");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setInt(1, userId);
		return pstmt.executeUpdate();
	}

	private int insertLoginDetails(int userId, User user) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.insert_user_login");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setInt(1, userId);
		pstmt.setString(2, user.getEmail());
		pstmt.setString(3, user.getPassword());
		return pstmt.executeUpdate();
	}

	@Override
	public ResultSet getUser(String email, String password) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.user_login");
		PreparedStatement pstmt = null;
		pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		return pstmt.executeQuery();
	}

	@Override
	public ResultSet findUserByMail(String mail) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.get_userby_mail");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setString(1, mail);
		return pstmt.executeQuery();
	}
}
