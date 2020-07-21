package com.emulous.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.emulous.dao.DatabaseImpl;
import com.emulous.model.Student;
import com.emulous.model.Subject;

public class StudentOperationImpl implements StudentOperationHandler{

	private static StudentOperationImpl student;
	
	private StudentOperationImpl() {
		
	}
	
	public static StudentOperationImpl getInstance() {
		if(student == null) {
			student = new StudentOperationImpl();
		}
		return student;
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
    			subject.setStaff(rs.getString(5));
    			subject.setMark(rs.getInt(6));
    			student.setSubjects(subject);
    		}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return student;
	}
	
	@Override
	public boolean isUserAvailable(String name, String password) {
		return DatabaseImpl.getInstance().isUserAvailable(name, password);
	}
}
