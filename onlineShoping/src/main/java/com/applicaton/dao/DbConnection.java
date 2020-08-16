package com.applicaton.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.applicaton.util.ConfigUtil;

public class DbConnection {
	private static DbConnection instance;
	private static Connection connection;

	private DbConnection() {
		try {
			Properties properties = ConfigUtil.loadProperty();
			String url = properties.getProperty("psql.CONNECTION_URL1");
			String userName = properties.getProperty("psql.username");
			String password = properties.getProperty("psql.password");
			String driverName = properties.getProperty("psql.CONNECTION_DRIVER");
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
