package com.web.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.dao.DatabaseImpl;
import com.web.model.Student;
import com.web.model.Subject;
import com.web.util.CommonUtil;

public class ExamServiceImpl implements ExamService {

	private static ExamServiceImpl instance;

	private ExamServiceImpl() {

	}

	public static ExamServiceImpl getInstance() {
		if (instance == null) {
			instance = new ExamServiceImpl();
		}
		return instance;
	}

	@Override
	public List<Student> getStudentsResults() {

		List<Student> students = getStudents();
		List<Student> passStudents = new ArrayList<>();
		List<Student> failStudents = new ArrayList<>();

		for (int i = 0; i < students.size(); ++i) {
			if (CommonUtil.isPass(students.get(i))) {
				passStudents.add(students.get(i));
			} else {
				failStudents.add(students.get(i));
			}
		}
		CommonUtil.sort(passStudents);
		CommonUtil.sort(failStudents);
		setStudentsRank(passStudents);
		for (Student student : failStudents) {
			passStudents.add(student);
		}
		return passStudents;
	}

	private void setStudentsRank(List<Student> students) {
		int rank = 0;
		int count = 0;
		List<Integer> mark = new ArrayList<>();
		for (int i = 0; i < students.size(); ++i) {
			if (!mark.contains(getTotalMark(students.get(i)))) {
				mark.add(getTotalMark(students.get(i)));
				rank = ++rank + count;
				count = 0;
			} else {
				count++;
			}
			students.get(i).setRank(rank);
		}
	}

	private int getTotalMark(Student student) {
		int sum = 0;
		for (int i = 0; i < student.getSubjects().size(); ++i) {
			sum += student.getSubjects().get(i).getMark();
		}
		return sum;
	}

	private List<Student> getStudents() {
		Map<Integer, Student> map = new HashMap<>();
		List<Student> students = new ArrayList<>();

		try {
			ResultSet rs = DatabaseImpl.getInstance().getData();
			while (rs.next()) {
				if (!map.containsKey(rs.getInt(1))) {
					Student student = new Student();
					student.setId(rs.getInt(1));
					student.setName(rs.getString(2));
					student.setDepartment(rs.getString(3));
					map.put(rs.getInt(1), student);
				}
				Subject subject = new Subject();
				subject.setName(rs.getString(4));
				subject.setStaff(rs.getString(5));
				subject.setMark(rs.getInt(6));
				map.get(rs.getInt(1)).setSubjects(subject);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		for (Map.Entry<Integer, Student> entry : map.entrySet()) {
			students.add(entry.getValue());
		}
		return students;
	}

	@Override
	public List<Student> getCollageTopStudents(int topRank) {
		List<Student> passStudents = getPassStudent();
		List<Student> topStudents = new ArrayList<>();

		CommonUtil.sort(passStudents);
		int count = 0;
		int rank = 0;
		List<Integer> mark = new ArrayList<>();
		for (int i = 0; i < passStudents.size(); ++i) {
			if (!mark.contains(getTotalMark(passStudents.get(i)))) {
				mark.add(getTotalMark(passStudents.get(i)));
				rank = ++rank + count;
				count = 0;
			} else {
				count++;
			}
			if (rank <= topRank) {
				passStudents.get(i).setRank(rank);
				topStudents.add(passStudents.get(i));
			} else {
				break;
			}
		}
		return topStudents;
	}

	private List<Student> getPassStudent() {
		List<Student> students = getStudents();
		List<Student> passStudents = new ArrayList<>();
		for (Student student : students) {
			if (CommonUtil.isPass(student)) {
				passStudents.add(student);
			}
		}
		return passStudents;
	}

	@Override
	public List<Student> getNthRankStudent(int n) {

		List<Student> passStudents = getPassStudent();
		List<Student> nthRankStudents = new ArrayList<>();
		CommonUtil.sort(passStudents);
		int count = 0;
		int rank = 0;
		List<Integer> mark = new ArrayList<>();
		for (int i = 0; i < passStudents.size(); ++i) {
			if (!mark.contains(getTotalMark(passStudents.get(i)))) {
				mark.add(getTotalMark(passStudents.get(i)));
				rank = ++rank + count;
				count = 0;
			} else {
				count++;
			}
			if (rank == n) {
				passStudents.get(i).setRank(rank);
				nthRankStudents.add(passStudents.get(i));
			}
			if (rank > n)
				break;
		}
		return nthRankStudents;
	}

}
