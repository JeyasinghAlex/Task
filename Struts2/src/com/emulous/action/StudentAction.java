package com.emulous.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.emulous.dao.DatabaseImpl;
import com.emulous.model.Student;
import com.emulous.service.OverallOperationImpl;
import com.emulous.service.StudentOperationHandler;
import com.opensymphony.xwork2.ActionSupport;

public class StudentAction extends ActionSupport{
	
	private Map<String, Integer> average;
	private Map<String, Integer> deptwisePassPercentage;
	private Map<String, Integer> stfwisePassPercentage;
	private List<List<String>> highMarkStudents;
	
	public String getEachSubjectAverageMark() {
		StudentOperationHandler impl = OverallOperationImpl.getInstance();
		average = impl.getEachSubjectAverageMark();
		if (average != null && !average.isEmpty()) {
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String getEachSubjectHighestMark() {
		StudentOperationHandler impl = OverallOperationImpl.getInstance();
		highMarkStudents = impl.getEachSubjectHighestMark();
		if (highMarkStudents != null && !highMarkStudents.isEmpty()) {
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String getDepartmentwisePassPercentage() {
		StudentOperationHandler impl = OverallOperationImpl.getInstance();
		deptwisePassPercentage = impl.getDepartmentwisePassPercentage();
		if (deptwisePassPercentage != null && !deptwisePassPercentage.isEmpty()) {
			return SUCCESS;
		}
		return ERROR;		
	}
	
	public String getStaffwisePassPercentage() {
		StudentOperationHandler impl = OverallOperationImpl.getInstance();
		stfwisePassPercentage = impl.getStaffwisePassPercentage();
		if (stfwisePassPercentage != null && !stfwisePassPercentage.isEmpty()) {
			return SUCCESS;
		}
		return ERROR;
	}
	

	public Map<String, Integer> getAverage() {
		return average;
	}

	public void setAverage(Map<String, Integer> average) {
		this.average = average;
	}

	public Map<String, Integer> getDeptwisePassPercentage() {
		return deptwisePassPercentage;
	}

	public void setDeptwisePassPercentage(Map<String, Integer> deptwisePassPercentage) {
		this.deptwisePassPercentage = deptwisePassPercentage;
	}

	public Map<String, Integer> getStfwisePassPercentage() {
		return stfwisePassPercentage;
	}

	public void setStfwisePassPercentage(Map<String, Integer> stfwisePassPercentage) {
		this.stfwisePassPercentage = stfwisePassPercentage;
	}

	public List<List<String>> getHighMarkStudents() {
		return highMarkStudents;
	}

	public void setHighMarkStudents(List<List<String>> highMarkStudents) {
		this.highMarkStudents = highMarkStudents;
	}
	
	
}
