package com.web.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.web.dao.DatabaseImpl;
import com.web.model.Department;
import com.web.model.Student;
import com.web.model.Subject;
import com.web.util.CommonUtil;

public class DepartmentServiceImpl implements DepartmentService {

	private static DepartmentServiceImpl instance = null;

	private DepartmentServiceImpl() {

	}

	public static DepartmentServiceImpl getInstance() {
		if (instance == null) {
			instance = new DepartmentServiceImpl();
		}
		return instance;
	}

	@Override
	public Map<String, Integer> getStaffsResults() {

		Map<String, List<Integer>> marks = new HashMap<>();
		Map<String, Integer> passPercentage = new HashMap<>();

		try {
			ResultSet rs = DatabaseImpl.getInstance().getStudentMark();
			while (rs.next()) {
				if (!marks.containsKey(rs.getString(1))) {
					marks.put(rs.getString(1), new ArrayList<Integer>());
				}
				marks.get(rs.getString(1)).add(rs.getInt(2));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		for (Map.Entry<String, List<Integer>> entry : marks.entrySet()) {
			int count = 0;
			int total = 0;
			List<Integer> list = entry.getValue();
			for (int ch : list) {
				if (ch < 35) {
					count++;
				}
				total++;
			}
			int pass = total - count;
			passPercentage.put(entry.getKey(), (pass * 100) / total);
		}
		return passPercentage;
	}

	@Override
	public Map<String, Integer> getDepartmentsResults() {

		Map<String, Integer> passPercentage = new HashMap<>();
		List<Student> students = getStudentsData();
		for (Department department : Department.values()) {
			int count = 0;
			int totalStudent = 0;
			boolean result = false;
			for (Student student : students) {
				if (student.getDepartment().equals(department.toString())) {
					result = true;
					if (!CommonUtil.isPass(student)) {
						++count;
					}
					++totalStudent;
				}
			}
			int pass = totalStudent - count;
			if (result)
				passPercentage.put(department.toString(), (pass * 100) / totalStudent);
		}
		return passPercentage;
	}

	private List<Student> getStudentsData() {

		List<Student> students = getStudents();
		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student student1, Student student2) {
				if (Department.valueOf(student1.getDepartment()).getOrder() > Department
						.valueOf(student2.getDepartment()).getOrder()) {
					return 1;
				}
				if (Department.valueOf(student1.getDepartment()).getOrder() == Department
						.valueOf(student2.getDepartment()).getOrder()) {
					if (student1.getId() > student2.getId()) {
						return 1;
					}
				}
				return -1;
			}
		});
		return students;

	}

	@Override
	public List<Student> getStudents() {
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
	public List<List<String>> getSubjectsHighestMark() {

		List<List<String>> topStudents = new ArrayList<>();
		List<String> innerList = new ArrayList<>();
		List<Student> students = getStudents();
		List<String> subName = getSubjects();

		for (String name : subName) {
			int max = Integer.MIN_VALUE;
			innerList = new ArrayList<>();
			for (Student student : students) {
				for (Subject subject : student.getSubjects()) {
					if (subject.getName().equals(name) && subject.getMark() > max) {
						max = subject.getMark();
					}
				}
			}
			for (Student student : students) {
				for (Subject subject : student.getSubjects()) {
					if (subject.getName().equals(name) && max == subject.getMark()) {
						innerList.add(student.getName());
						innerList.add(subject.getName());
						innerList.add(subject.getStaff());
						innerList.add(String.valueOf(subject.getMark()));
						break;
					}
				}
			}
			topStudents.add(innerList);
		}
		return topStudents;
	}

	private List<String> getSubjects() {
		List<String> subName = new ArrayList<>();
		try {
			ResultSet rs = DatabaseImpl.getInstance().getSubjects();
			while (rs.next()) {
				subName.add(rs.getString(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return subName;
	}

	@Override
	public List<Student> getDepartmentsTopStudents(int limit) {

		List<Student> passStudents = getPassStudent();
		Collections.sort(passStudents, new Comparator<Student>() {
			@Override
			public int compare(Student student1, Student student2) {
				int sum1 = 0;
				int sum2 = 0;
				for (Subject subject : student1.getSubjects()) {
					sum1 += subject.getMark();
				}
				for (Subject subject : student2.getSubjects()) {
					sum2 += subject.getMark();
				}
				if (Department.valueOf(student1.getDepartment()).getOrder() > Department
						.valueOf(student2.getDepartment()).getOrder()) {
					return 1;
				}
				if (Department.valueOf(student1.getDepartment()).getOrder() == Department
						.valueOf(student2.getDepartment()).getOrder()) {
					if (sum1 < sum2) {
						return 1;
					}
				}
				return -1;
			}
		});

		List<Integer> mark = new ArrayList<>();
		List<Student> topStudents = new ArrayList<>();

		for (Department department : Department.values()) {
			int count = 0;
			int rank = 0;
			mark = new ArrayList<>();
			for (int i = 0; i < passStudents.size(); ++i) {
				if (passStudents.get(i).getDepartment().equals(department.toString())) {
					if (!mark.contains(getTotalMark(passStudents.get(i)))) {
						mark.add(getTotalMark(passStudents.get(i)));
						rank = ++rank + count;
						count = 0;
					} else {
						count++;
					}
					if (rank <= limit) {
						passStudents.get(i).setRank(rank);
						topStudents.add(passStudents.get(i));
					} else {
						break;
					}
				}
			}
		}
		return topStudents;
	}

	private int getTotalMark(Student student) {
		int sum = 0;
		for (int i = 0; i < student.getSubjects().size(); ++i) {
			sum += student.getSubjects().get(i).getMark();
		}
		return sum;
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
	public Map<String, Integer> getSubjectsAverage() {

		Map<String, Integer> average = new HashMap<>();
		Map<String, List<Integer>> marks = new HashMap<>();
		List<String> subName = getSubjects();

		try {
			ResultSet rs = DatabaseImpl.getInstance().getMarks();
			while (rs.next()) {
				if (!marks.containsKey(rs.getString(1))) {
					marks.put(rs.getString(1), new ArrayList<Integer>());
				}
				marks.get(rs.getString(1)).add(rs.getInt(2));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		for (String name : subName) {
			int sum = 0;
			int avrg = 0;
			try {
				List<Integer> mark = marks.get(name);
				for (int ch : mark) {
					sum += ch;
				}
				avrg = sum / mark.size();
			} catch (NullPointerException ex) {
				// print log file
				avrg = 0;
			}
			average.put(name, avrg);
		}
		return average;
	}

	@Override
	public Set<String> getSubject(int studentId) {
		Set<String> subjects = new HashSet<>();
		try {
			ResultSet rs = DatabaseImpl.getInstance().getSubjects(studentId);
			while (rs.next()) {
				subjects.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjects;
	}

	@Override
	public Set<String> getDepartments() {
		Set<String> subjects = new HashSet<>();
		try {
			ResultSet rs = DatabaseImpl.getInstance().getDepartments();
			while (rs.next()) {
				subjects.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjects;
	}
}
