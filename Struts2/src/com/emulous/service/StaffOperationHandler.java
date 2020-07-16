package com.emulous.service;

import java.util.List;
import java.util.Map;

import com.emulous.model.Student;

public interface StaffOperationHandler {
	
	List<Student> getStudentsData();
	List<Student> getCollageTopStudents();
	List<Student> getEachDeptTopStudents();
	List<Student> getNthRankStudent(int n);
	List<Student> getAllStudentsRankwise();
	Map<String, Integer> getEachSubjectAverageMark();
	List<List<String>> getEachSubjectHighestMark();
	Map<String, Integer> getDepartmentwisePassPercentage();
	Map<String, Integer> getStaffwisePassPercentage();
	
}
