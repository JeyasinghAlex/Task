package com.applicaton.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdminDao {

	public ResultSet getAdmin(String userName, String password) throws SQLException;
}
