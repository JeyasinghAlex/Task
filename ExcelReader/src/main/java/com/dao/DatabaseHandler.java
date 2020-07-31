package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Student;

public interface DatabaseHandler {
	
	ResultSet get() throws SQLException;
	void insert(Student student) throws SQLException;
}
