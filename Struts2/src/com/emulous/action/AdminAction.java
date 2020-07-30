package com.emulous.action;

import java.util.List;
import java.util.Map;

import com.emulous.model.Student;
import com.emulous.service.OverallOperationImpl;
import com.emulous.service.StaffOperationHandler;
import com.emulous.service.StudentOperationHandler;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport{
	
	private int rank;
	private Map<String, Integer> average;
	private Map<String, Integer> deptwisePassPercentage;
	private Map<String, Integer> stfwisePassPercentage;
	
	private List<List<String>> highMarkStudents;
	private List<Student> studentsData;
	private List<Student> clgTopStudens;
	private List<Student> eachDeptTopStudens;
	private List<Student> nthRankStudent;
	private List<Student> studentsRank;
	
	public String getAllStudentsData() {
		StaffOperationHandler impl = OverallOperationImpl.getInstance();
		studentsData = impl.getStudentsData();
		if (studentsData != null && !studentsData.isEmpty()) {
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String getTopStudents() {
		StaffOperationHandler impl = OverallOperationImpl.getInstance();
		clgTopStudens = impl.getCollageTopStudents();
		if (clgTopStudens != null && !clgTopStudens.isEmpty()) {
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String getEachDeptTopStudents() {
		StaffOperationHandler impl = OverallOperationImpl.getInstance();
		eachDeptTopStudens = impl.getEachDeptTopStudents();
		if (eachDeptTopStudens != null && !eachDeptTopStudens.isEmpty()) {
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String getNthRankStudents() {
		StaffOperationHandler impl = OverallOperationImpl.getInstance();
		nthRankStudent = impl.getNthRankStudent(getRank());
		if (nthRankStudent != null && !nthRankStudent.isEmpty()) {
			return SUCCESS;
		}
		return ERROR;
	}
	
	
	public String getStudentsRankwise() {
		StaffOperationHandler impl = OverallOperationImpl.getInstance();
		studentsRank = impl.getAllStudentsRankwise();
		if (studentsRank != null && !studentsRank.isEmpty()) {
			return SUCCESS;
		}
		return ERROR;
	}
	
	
	public String getEachSubjectAverageMark() {
		StaffOperationHandler impl = OverallOperationImpl.getInstance();
		average = impl.getEachSubjectAverageMark();
		if (average != null && !average.isEmpty()) {
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String getEachSubjectHighestMark() {
		StaffOperationHandler impl = OverallOperationImpl.getInstance();
		highMarkStudents = impl.getEachSubjectHighestMark();
		if (highMarkStudents != null && !highMarkStudents.isEmpty()) {
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String getDepartmentwisePassPercentage() {
		StaffOperationHandler impl = OverallOperationImpl.getInstance();
		deptwisePassPercentage = impl.getDepartmentwisePassPercentage();
		if (deptwisePassPercentage != null && !deptwisePassPercentage.isEmpty()) {
			return SUCCESS;
		}
		return ERROR;		
	}
	
	public String getStaffwisePassPercentage() {
		StaffOperationHandler impl = OverallOperationImpl.getInstance();
		stfwisePassPercentage = impl.getStaffwisePassPercentage();
		if (stfwisePassPercentage != null && !stfwisePassPercentage.isEmpty()) {
			return SUCCESS;
		}
		return ERROR;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
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

	public List<Student> getClgTopStudens() {
		return clgTopStudens;
	}

	public void setClgTopStudens(List<Student> clgTopStudens) {
		this.clgTopStudens = clgTopStudens;
	}

	public List<Student> getEachDeptTopStudens() {
		return eachDeptTopStudens;
	}

	public void setEachDeptTopStudens(List<Student> eachDeptTopStudens) {
		this.eachDeptTopStudens = eachDeptTopStudens;
	}

	public List<Student> getStudentsRank() {
		return studentsRank;
	}

	public void setStudentsRank(List<Student> studentsRank) {
		this.studentsRank = studentsRank;
	}

	public void setStudentsData(List<Student> studentsData) {
		this.studentsData = studentsData;
	}

	public void setNthRankStudents(List<Student> nthRankStudents) {
		this.nthRankStudent = nthRankStudents;
	}

	public List<Student> getNthRankStudent() {
		return nthRankStudent;
	}

	public void setNthRankStudent(List<Student> nthRankStudent) {
		this.nthRankStudent = nthRankStudent;
	}

	public List<Student> getStudentsData() {
		return studentsData;
	}
	
	
	
	
}
