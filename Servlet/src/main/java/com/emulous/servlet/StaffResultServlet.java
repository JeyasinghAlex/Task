package com.emulous.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emulous.model.Student;
import com.emulous.service.DepartmentHandler;
import com.emulous.service.StaffOperationImpl;
import com.google.gson.Gson;

public class StaffResultServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DepartmentHandler operation = DepartmentHandler.getInstance();
		Map<String, Integer> passPercentage = operation.getStaffwisePassPercentage();
		request.setAttribute("passPercentage", passPercentage);
		request.getRequestDispatcher("/staffView.jsp").forward(request, response);
//		String jsonStr = new Gson().toJson(students);
//	    response.setContentType("application/json");
//	    response.setCharacterEncoding("UTF-8");
//	    response.getWriter().write(jsonStr);
//	    response.setStatus(200);
	}

}
