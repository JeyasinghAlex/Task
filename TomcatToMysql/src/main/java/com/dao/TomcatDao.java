package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class TomcatDao {
	
	private static TomcatDao instance;
	private static Connection connection;
	
	private TomcatDao() {
		try {
			Context initCtx = new InitialContext();
	        Context envCtx = (Context) initCtx.lookup("java:comp/env");
	        DataSource ds = (DataSource) envCtx.lookup("jdbc/tomcat_status");
			connection = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static TomcatDao getInstance() {
		if (instance == null) {
			instance = new TomcatDao();
		}
		return instance;
	}
	
	public void insert() throws SQLException {
		String query = "INSERT INTO status(name) VALUES(?)";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, "started");
		pstmt.executeUpdate();
	}
}
