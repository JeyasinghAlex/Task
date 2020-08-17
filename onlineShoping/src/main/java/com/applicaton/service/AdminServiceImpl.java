package com.applicaton.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.applicaton.dao.AdminDao;
import com.applicaton.dao.AdminDaoImpl;

public class AdminServiceImpl implements AdminService {

	private static AdminServiceImpl instance;

	private AdminServiceImpl() {

	}

	public static AdminServiceImpl getInstance() {
		if (instance == null) {
			instance = new AdminServiceImpl();
		}
		return instance;
	}

	@Override
	public int isAdminAvailable(String userName, String password) {

		int adminId = 0;
		AdminDao dao = AdminDaoImpl.getInstance();
		try {
			ResultSet rs = dao.getAdmin(userName, password);
			if (rs.next()) {
				adminId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adminId;
	}

}
