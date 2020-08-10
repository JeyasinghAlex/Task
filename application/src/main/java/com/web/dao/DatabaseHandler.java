package com.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface DatabaseHandler {

	void initializeDatabase() throws ClassNotFoundException, SQLException;

	ResultSet getMarks() throws SQLException;

	ResultSet getSubjects() throws SQLException;

	ResultSet getData() throws SQLException;

	ResultSet getStudentMark() throws SQLException;

	ResultSet getSubjects(int studentId) throws SQLException;

	ResultSet getDepartments() throws SQLException;

	ResultSet getRow(int id) throws SQLException;

	int updateRow(int studentId, int subjectId, int mark) throws SQLException;

	int[] insertRows(int studentId, Map<String, Integer> marks) throws SQLException;

	ResultSet isAdmin(String name, String password) throws SQLException;

	ResultSet isStaff(String name, String password) throws SQLException;

	ResultSet isStudent(String name, String password) throws SQLException;
}
