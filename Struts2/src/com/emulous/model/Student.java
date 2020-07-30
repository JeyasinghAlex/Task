package com.emulous.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
	
	private int rank;
	private int id;
    private String name;
    private String department;
    private List<Subject> subjects;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject subject) {
        if (subjects == null) {
            subjects = new ArrayList<>();
        }
        this.subjects.add(subject);
    }
    
    

    public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
