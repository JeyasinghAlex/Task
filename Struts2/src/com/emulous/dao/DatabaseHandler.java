package com.emulous.dao;

import java.util.List;
import java.util.Map;

import com.emulous.model.Student;

public interface DatabaseHandler{
	List<Student> getResult();
	List<String> getSubjectsName();
	Map<String, List<Integer>> getSubjectMark();
	Map<String, List<Integer>> getStudentMark();
}
