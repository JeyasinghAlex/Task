package com.web.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.web.dao.AdminDaoImpl;
import com.web.dao.DatabaseImpl;
import com.web.model.Student;

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

	public boolean isAdminAvailable(String name, String password) {
		try {
			ResultSet rs = DatabaseImpl.getInstance().isAdmin(name, password);
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isStaffAvailable(String name, String password) {
		try {
			ResultSet rs = DatabaseImpl.getInstance().isStaff(name, password);
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isStudentAvailable(String name, String password) {
		try {
			ResultSet rs = DatabaseImpl.getInstance().isStudent(name, password);
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addDepartment(String jsonStr) {
		Set<String> subjects = new HashSet<>();
		JSONObject obj = new JSONObject(jsonStr);
		String department = (String) obj.get("department");
		JSONArray arr = (JSONArray) obj.get("subjects");
		for (int i = 0; i < arr.length(); i++) {
			subjects.add(arr.getString(i));
		}
		try {
			int id = AdminDaoImpl.getInstance().insertDepartment(department, subjects);
			if (id != 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Set<String> getSubjects() {
		Set<String> subjects = new HashSet<>();
		try {
			ResultSet rs = AdminDaoImpl.getInstance().getSubjects();
			while (rs.next()) {
				subjects.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjects;
	}

	public boolean addStaff(String jsonStr) {
		Set<String> subjects = new HashSet<>();
		JSONObject obj = new JSONObject(jsonStr);
		String name = (String) obj.get("name");
		JSONArray arr = (JSONArray) obj.get("subjects");
		for (int i = 0; i < arr.length(); i++) {
			subjects.add(arr.getString(i));
		}
		try {
			int id = AdminDaoImpl.getInstance().insertStaff(name, subjects);
			if (id != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addStudent(Student student) {
		try {
			int id = AdminDaoImpl.getInstance().insertStudent(student);
			if (id != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeStudent(int id) {
		try {
			int result = AdminDaoImpl.getInstance().deleteRow(id);
			if (result != 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
