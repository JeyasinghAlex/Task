package com.emulous.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emulous.dao.DatabaseImpl;
import com.emulous.model.Student;
import com.emulous.model.Subject;

public class OverallOperationImpl implements StudentOperationHandler, StaffOperationHandler{

	private static OverallOperationImpl impl;
	
	private OverallOperationImpl() {
		
	}
	
	
	public static OverallOperationImpl getInstance() {
		if (impl == null) {
			impl = new OverallOperationImpl();
		}
		return impl;
	}
	
	
	
	@Override
	public List<Student> getStudentsData() {
		
        List<Student> students = DatabaseImpl.getInstance().getResult();
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                if (Department.valueOf(student1.getDepartment()).getOrder() > Department.valueOf(student2.getDepartment()).getOrder()) {
                    return 1;
                }
                if (Department.valueOf(student1.getDepartment()).getOrder() == Department.valueOf(student2.getDepartment()).getOrder()) {
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
	public List<Student> getCollageTopStudents() {
		List<Student> passStudents = getPassStudent();
		List<Student> topStudents = new ArrayList<>();
		
        sort(passStudents);
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
            if (rank <= 3) {
            	passStudents.get(i).setRank(rank);
            	topStudents.add(passStudents.get(i));
            }else {
            	 break;
            }
        }
        return topStudents;		
	}
	
	
	private List<Student>  getPassStudent() {
        List<Student> students = DatabaseImpl.getInstance().getResult();
        List<Student> passStudents = new ArrayList<>();
        for (Student student : students) {
            if (isPass(student)) {
                passStudents.add(student);
            }
        }
        return passStudents;
    }
	
	private int getTotalMark(Student student) {
        int sum = 0;
        for (int i = 0; i < student.getSubjects().size(); ++i) {
            sum += student.getSubjects().get(i).getMark();
        }
        return sum;
    }

	@Override
	public List<Student> getEachDeptTopStudents() {
		
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
                if (Department.valueOf(student1.getDepartment()).getOrder() > Department.valueOf(student2.getDepartment()).getOrder()) {
                    return 1;
                }
                if (Department.valueOf(student1.getDepartment()).getOrder() == Department.valueOf(student2.getDepartment()).getOrder()) {
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
                    if (rank <= 3) {
                    	passStudents.get(i).setRank(rank);
                        topStudents.add(passStudents.get(i));
                    }else {
                    	break;
                    }
                }
            }                       
        }
        return topStudents;
	}

	@Override
	public List<Student> getNthRankStudent(int n) {
		
		List<Student> passStudents = getPassStudent();
		List<Student> nthRankStudents = new ArrayList<>();
        sort(passStudents);
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
        setStudentsRank(nthRankStudents);
        return nthRankStudents;
	}

	@Override
	public List<Student> getAllStudentsRankwise() {
		
        List<Student> students = DatabaseImpl.getInstance().getResult();
        List<Student> passStudents = new ArrayList<>();
        List<Student> failStudents = new ArrayList<>();        

        for (int i = 0; i < students.size(); ++i) {
            if (isPass(students.get(i))) {
                passStudents.add(students.get(i));
            } else {
                failStudents.add(students.get(i));
            }
        }
        sort(passStudents);
        sort(failStudents);
        setStudentsRank(passStudents);
        for (Student student : failStudents) {
        	passStudents.add(student);
        }
        return passStudents;
	}
	
	private void sort(List<Student> students) {
        Collections.sort(students, new Comparator<Student>() {
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
                if (sum1 < sum2) {
                    return 1;
                } else if (sum1 == sum2) {
                    return 0;
                }
                return -1;
            }
        });
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
	
	private boolean isPass(Student student) {
        for (int i = 0; i < student.getSubjects().size(); ++i) {
            if (student.getSubjects().get(i).getMark() < 35) {
                return false;
            }
        }
        return true;
    }
	
	@Override
	public Map<String, Integer> getEachSubjectAverageMark() {
		
        List<String> subName = DatabaseImpl.getInstance().getSubjectsName();
        Map<String, Integer> average = new HashMap<>();
        Map<String, List<Integer>> marks = DatabaseImpl.getInstance().getSubjectMark();

        for (String name : subName) {
            int sum = 0;
            List<Integer> mark = marks.get(name);
            for (int ch : mark) {
                sum += ch;
            }
            average.put(name, sum / mark.size());
        }
        return average;
	}

	@Override
	public List<List<String>> getEachSubjectHighestMark() {
		
		List<Student> students = DatabaseImpl.getInstance().getResult();
        List<String> subName = DatabaseImpl.getInstance().getSubjectsName();
        List<List<String>> topStudents = new ArrayList<>();
        List<String> innerList = new ArrayList<>();
        
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

	@Override
	public Map<String, Integer> getDepartmentwisePassPercentage() {
		
        Map<String, Integer> passPercentage = new HashMap<>();
        List<Student> students = getStudentsData();
        for (Department department : Department.values()) {
            int count = 0;
            int totalStudent = 0;
            for (Student student : students) {
                if (student.getDepartment().equals(department.toString())) {
                    if (!isPass(student)) {
                        ++count;
                    }
                    ++totalStudent;
                }
            }
            int pass = totalStudent - count;
            passPercentage.put(department.toString(), (pass * 100) / totalStudent);
        }
        return passPercentage;
	}

	@Override
	public Map<String, Integer> getStaffwisePassPercentage() {
		
		Map<String, List<Integer>> marks = DatabaseImpl.getInstance().getStudentMark();
        Map<String, Integer> passPercentage = new HashMap<>();
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
	
	
    private enum Department {
        mech(0), civil(1), eee(2), ece(3), cse(4);
        int order;

        Department(int i) {
            this.order = i;
        }

        public int getOrder() {
            return order;
        }
    }
}
