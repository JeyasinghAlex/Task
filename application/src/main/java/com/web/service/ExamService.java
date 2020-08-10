package com.web.service;

import java.util.List;

import com.web.model.Student;

public interface ExamService {

	List<Student> getStudentsResults();

	List<Student> getCollageTopStudents(int topRank);

	List<Student> getNthRankStudent(int n);
}
