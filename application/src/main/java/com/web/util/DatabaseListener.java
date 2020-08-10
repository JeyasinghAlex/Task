package com.web.util;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.web.dao.DatabaseImpl;

public class DatabaseListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent context) {
		try {
			DatabaseImpl.getInstance().initializeDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}
}
