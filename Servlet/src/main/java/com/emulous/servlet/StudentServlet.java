package com.emulous.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emulous.model.Student;
import com.emulous.service.StudentOperationHandler;
import com.emulous.service.StudentOperationImpl;

public class StudentServlet extends HttpServlet {

//	http://localhost:8081/Servlet/api/student?id=1&name=alex
//	http://localhost:8081/Servlet/api/student?id=1,2
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Student> students = new ArrayList<>();
		String id = request.getParameter("studentid");
		StudentOperationHandler operation = StudentOperationImpl.getInstance();
		Student student = operation.getStudent(Integer.parseInt(id));
		students.add(student);
		request.setAttribute("listStudents", students);
		request.getRequestDispatcher("/studentView.jsp").forward(request, response);
	}

}
