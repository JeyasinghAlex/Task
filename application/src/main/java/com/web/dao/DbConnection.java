package com.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.web.util.ConfigUtil;

public class DbConnection {

	private static DbConnection instance;
	private static Connection connection;

	private DbConnection() {
		try {
			Properties properties = ConfigUtil.loadProperty();
			String url = properties.getProperty("mysql.CONNECTION_URL1");
			String userName = properties.getProperty("mysql.username");
			String password = properties.getProperty("mysql.password");
			String driverName = properties.getProperty("mysql.CONNECTION_DRIVER");
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException | SQLException | NullPointerException ex) {
			ex.printStackTrace();
		}
	}

	public static DbConnection getInstance() {
		if (instance == null) {
			instance = new DbConnection();
		}
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
