package com.emulous.service;

import java.util.List;
import java.util.Map;

import com.emulous.model.Student;

public interface StudentOperationHandler {
	
	Map<String, Integer> getEachSubjectAverageMark();
	List<List<String>> getEachSubjectHighestMark();
	Map<String, Integer> getDepartmentwisePassPercentage();
	Map<String, Integer> getStaffwisePassPercentage();

}
