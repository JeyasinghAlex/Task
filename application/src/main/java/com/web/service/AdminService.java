package com.web.service;

import java.util.Set;

import com.web.model.Student;

public interface AdminService {

	boolean isAdminAvailable(String name, String password);

	boolean isStaffAvailable(String name, String password);

	boolean isStudentAvailable(String name, String password);

	boolean addDepartment(String jsonStr);

	Set<String> getSubjects();

	boolean addStaff(String jsonStr);

	boolean addStudent(Student student);

	boolean removeStudent(int id);
}
