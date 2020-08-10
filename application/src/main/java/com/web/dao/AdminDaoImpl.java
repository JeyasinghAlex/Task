package com.web.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.web.model.Student;
import com.web.util.ConfigUtil;

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

	@Override
	public int insertStudent(Student student) throws SQLException {
		int studentId = 0;
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.insert_student");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, student.getName());
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
			studentId = rs.getInt(1);
		}
		insertStudentToDepartment(studentId, student.getDepartment());
		return studentId;
	}

	private void insertStudentToDepartment(int studentId, String department) throws SQLException {
		int departmentId = getDepartmentId(department);
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.insert_student_to_department");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setInt(1, studentId);
		pstmt.setInt(2, departmentId);
		pstmt.executeUpdate();
	}

	private int getDepartmentId(String department) throws SQLException {
		int departmentId = 0;
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.get_department");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setString(1, department);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			departmentId = rs.getInt(1);
		}
		return departmentId;
	}

	@Override
	public int insertStaff(String name, Set<String> subjects) throws SQLException {
		int staffId = 0;
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.insert_staff");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, name);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
			staffId = rs.getInt(1);
		}
		insertStaffToSubject(staffId, subjects);
		return staffId;
	}

	private void insertStaffToSubject(int staffId, Set<String> subjects) throws SQLException {
		List<Integer> subjectId = getSubjectId(subjects);
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.insert_staff_to_subject");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		for (int id : subjectId) {
			pstmt.setInt(1, staffId);
			pstmt.setInt(2, id);
			pstmt.addBatch();
		}
		pstmt.executeBatch();
	}

	private List<Integer> getSubjectId(Set<String> subjects) throws SQLException {
		List<Integer> subjectId = new ArrayList<>();
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.get_subject");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		for (String subject : subjects) {
			pstmt.setString(1, subject);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				subjectId.add(rs.getInt(1));
			}
		}
		return subjectId;
	}

	@Override
	public int insertDepartment(String department, Set<String> subjects) throws SQLException {
		int departmentId = 0;
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.insert_department");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, department);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
			departmentId = rs.getInt(1);
		}
		insertSubject(subjects);
		insertDepartmentToSubject(departmentId, subjects);
		return departmentId;
	}

	private void insertDepartmentToSubject(int departmentId, Set<String> subjects) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.insert_department_to_subject");
		List<Integer> subjectId = getSubjectId(subjects);
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		for (int id : subjectId) {
			pstmt.setInt(1, departmentId);
			pstmt.setInt(2, id);
			pstmt.addBatch();
		}
		pstmt.executeBatch();
	}

	private void insertSubject(Set<String> subjects) throws SQLException {
		Set<String> dbSubjects = new HashSet<>();
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.insert_subject");
		ResultSet rs = getSubjects();
		while (rs.next()) {
			dbSubjects.add(rs.getString(1));
		}

		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		for (String subName : subjects) {
			if (!dbSubjects.contains(subName)) {
				pstmt.setString(1, subName);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		}
	}

	@Override
	public ResultSet getSubjects() throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.get_subjects");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		return pstmt.executeQuery();
	}

	@Override
	public ResultSet isAdminAvailable(String name, String password) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.is_admin");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setString(1, name);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	@Override
	public int deleteRow(int id) throws SQLException {
		Properties properties = ConfigUtil.loadProperty();
		String query = properties.getProperty("mysql.query.delete");
		PreparedStatement pstmt = DbConnection.getInstance().getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		return pstmt.executeUpdate();
	}
}
