package com.emulous.service;

import java.util.List;

import com.emulous.model.Student;

public interface StaffOperationHandler {
	
	List<Student> getStudentsRankwise();
	List<Student> getCollageTopStudents(int topRank);
	List<Student> getEachDeptTopStudents(int topRank);
	List<Student> getNthRankStudent(int n);
	boolean isStaffAvailable(String name, String password);
}
