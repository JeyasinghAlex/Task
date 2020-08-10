package com.web.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.web.dao.DatabaseImpl;
import com.web.model.Student;
import com.web.model.Subject;

public class StudentServiceImpl implements StudentService {

	private static StudentServiceImpl instance;

	private StudentServiceImpl() {

	}

	public static StudentServiceImpl getInstance() {
		if (instance == null) {
			instance = new StudentServiceImpl();
		}
		return instance;
	}

	@Override
	public Student getStudent(int id) {
		Student student = new Student();
		try {
			ResultSet rs = DatabaseImpl.getInstance().getRow(id);
			while (rs.next()) {
				Subject subject = new Subject();
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setDepartment(rs.getString(3));
				subject.setName(rs.getString(4));
				subject.setMark(rs.getInt(5));
				student.setSubjects(subject);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return student;
	}

	@Override
	public boolean updateMark(int studentId, int subjectId, int mark) {
		try {
			int numberOfUpdatedRows = DatabaseImpl.getInstance().updateRow(studentId, subjectId, mark);
			if (numberOfUpdatedRows != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addMarks(int studentId, Map<String, Integer> marks) {
		try {
			int[] arr = DatabaseImpl.getInstance().insertRows(studentId, marks);
			if (arr.length != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}