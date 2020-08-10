package com.web.service;

import java.util.Map;

import com.web.model.Student;

public interface StudentService {

	Student getStudent(int id);

	boolean updateMark(int studentId, int subjectId, int mark);

	boolean addMarks(int studentId, Map<String, Integer> marks);
}
