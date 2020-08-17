package com.applicaton.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.applicaton.util.ConfigUtil;

public class AdminDaoImpl implements AdminDao {

	private static AdminDaoImpl instance;

	private AdminDaoImpl() {

	}

	public static AdminDaoImpl getInstance() {
		if (instance == null) {
			instance = new AdminDaoImpl();
		}
		return instance;
	}

	public void initializeDatabase() throws ClassNotFoundException, SQLException {
		Properties propertice = ConfigUtil.loadProperty();
		Connection conn = null;
		String url = propertice.getProperty("psql.CONNECTION_URL");
		String user = propertice.getProperty("psql.username");
		String password = propertice.getProperty("psql.password");
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(url, user, password);
		System.out.println("Creating database and tables ...");
		PreparedStatement pstmt = conn.prepareStatement(propertice.getProperty("psql.query.database"));
		pstmt.executeUpdate();
		url = propertice.getProperty("psql.CONNECTION_URL1");
		conn = DriverManager.getConnection(url, user, password);
		pstmt = conn.prepareStatement(propertice.getProperty("psql.query.table1"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("psql.query.table2"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("psql.query.table3"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("psql.query.table4"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("psql.query.table5"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("psql.query.insert_admin_password"));
		pstmt.executeUpdate();
		System.out.println("Database ready ...");
	}

	@Override
	public ResultSet getAdmin(String userName, String password) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("psql.query.user_login");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setString(1, userName);
		pstmt.setString(2, password);
		return pstmt.executeQuery();
	}

}
