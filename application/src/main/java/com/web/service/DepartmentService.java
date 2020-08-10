package com.web.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.web.model.Student;

public interface DepartmentService {

	Map<String, Integer> getStaffsResults();

	Map<String, Integer> getDepartmentsResults();

	List<Student> getStudents();

	List<List<String>> getSubjectsHighestMark();

	List<Student> getDepartmentsTopStudents(int limit);

	Map<String, Integer> getSubjectsAverage();

	Set<String> getSubject(int studentId);

	Set<String> getDepartments();
}
