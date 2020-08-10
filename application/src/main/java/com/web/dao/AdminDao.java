package com.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.web.model.Student;

public interface AdminDao {

	int insertStudent(Student student) throws SQLException;

	int insertStaff(String name, Set<String> subjects) throws SQLException;

	int insertDepartment(String department, Set<String> subjects) throws SQLException;

	ResultSet getSubjects() throws SQLException;

	ResultSet isAdminAvailable(String name, String password) throws SQLException;

	int deleteRow(int id) throws SQLException;
}
