package com.emulous.service;

import java.util.List;

import com.emulous.model.Student;

public interface StudentOperationHandler {
	Student getStudent(int id);
	boolean isUserAvailable(String name, String password);
}
