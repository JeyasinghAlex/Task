package com.tomcat;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dao.TomcatDao;

public class Listener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent context) {
	     try {
	     	TomcatDao.getInstance().insert();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }
}
