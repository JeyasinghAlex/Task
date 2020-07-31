package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.model.Student;
import com.utils.CommonUtil;

public class DatabaseImpl implements DatabaseHandler {

	private static DatabaseImpl instance;
	private Connection connection;

	private DatabaseImpl() {
		try {
			Properties properties = CommonUtil.loadProperty();
			String url = properties.getProperty("mysql.CONNECTION_URL");
			String userName = properties.getProperty("mysql.username");
			String password = properties.getProperty("mysql.password");
			String driverName = properties.getProperty("mysql.CONNECTION_DRIVER");
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException | SQLException | NullPointerException ex) {
			ex.printStackTrace();
		}
	}

	public static DatabaseImpl getInstance() {
		if (instance == null) {
			instance = new DatabaseImpl();
		}
		return instance;
	}

	@Override
	public ResultSet get() throws SQLException {
		Properties properties = CommonUtil.loadProperty();
		String query = properties.getProperty("mysql.query.get");
		PreparedStatement pstmt = connection.prepareStatement(query);
		return pstmt.executeQuery();
	}

	@Override
	public void insert(Student student) throws SQLException {
		Properties properties = CommonUtil.loadProperty();
		String query = properties.getProperty("mysql.query.insert");
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, student.getId());
		pstmt.setString(2, student.getName());
		pstmt.executeUpdate();
	}
}
