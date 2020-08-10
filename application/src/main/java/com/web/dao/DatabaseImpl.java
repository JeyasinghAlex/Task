package com.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import com.web.util.ConfigUtil;

public class DatabaseImpl implements DatabaseHandler {

	private static DatabaseImpl instance;

	private DatabaseImpl() {

	}

	public static DatabaseImpl getInstance() {
		if (instance == null) {
			instance = new DatabaseImpl();
		}
		return instance;
	}

	@Override
	public void initializeDatabase() throws ClassNotFoundException, SQLException {
		Properties propertice = ConfigUtil.loadProperty();
		Connection conn = null;
		String url = propertice.getProperty("mysql.CONNECTION_URL");
		String user = propertice.getProperty("mysql.username");
		String password = propertice.getProperty("mysql.password");
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, password);
		System.out.println("Creating database and tables ...");
		PreparedStatement pstmt = conn.prepareStatement(propertice.getProperty("mysql.query.database"));
		pstmt.executeUpdate();
		url = propertice.getProperty("mysql.CONNECTION_URL1");
		conn = DriverManager.getConnection(url, user, password);
		pstmt = conn.prepareStatement(propertice.getProperty("mysql.query.table1"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("mysql.query.table2"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("mysql.query.table3"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("mysql.query.table4"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("mysql.query.table5"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("mysql.query.table6"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("mysql.query.table7"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("mysql.query.table8"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("mysql.query.user"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("mysql.query.staff"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("mysql.query.admin"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("mysql.query.user_login"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("mysql.query.staff_login"));
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(propertice.getProperty("mysql.query.admin_login"));
		pstmt.executeUpdate();
	}

	@Override
	public ResultSet getMarks() throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.get_marks");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	@Override
	public ResultSet getSubjects() throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.get_subjects_name");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	@Override
	public ResultSet getData() throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.get_result");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	@Override
	public ResultSet getStudentMark() throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.get_staffs_and_marks");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	@Override
	public ResultSet getSubjects(int studentId) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.get_department_subject");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setInt(1, studentId);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	@Override
	public ResultSet getDepartments() throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.get_departments");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	@Override
	public ResultSet getRow(int id) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.get_student_mark");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	@Override
	public int updateRow(int studentId, int subjectId, int mark) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.update");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setInt(1, mark);
		pstmt.setInt(2, subjectId);
		pstmt.setInt(3, studentId);
		return pstmt.executeUpdate();
	}

	@Override
	public int[] insertRows(int studentId, Map<String, Integer> marks) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.insert_marks");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		for (Map.Entry<String, Integer> entry : marks.entrySet()) {
			pstmt.setInt(1, studentId);
			pstmt.setInt(2, getSubjectId(entry.getKey()));
			pstmt.setInt(3, entry.getValue());
			pstmt.addBatch();
		}
		return pstmt.executeBatch();
	}

	private int getSubjectId(String subject) throws SQLException {
		int subjectId = 0;
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.get_subject");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setString(1, subject);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			subjectId = rs.getInt(1);
		}
		return subjectId;
	}

	@Override
	public ResultSet isAdmin(String name, String password) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.is_admin");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setString(1, name);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	@Override
	public ResultSet isStaff(String name, String password) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.is_staff");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setString(1, name);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	@Override
	public ResultSet isStudent(String name, String password) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.is_student");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setString(1, name);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
}
