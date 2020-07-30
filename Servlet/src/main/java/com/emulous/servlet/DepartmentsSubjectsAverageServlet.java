package com.emulous.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emulous.service.StaffOperationImpl;
import com.emulous.service.DepartmentHandler;
import com.google.gson.Gson;

public class DepartmentsSubjectsAverageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DepartmentHandler operations = DepartmentHandler.getInstance();
		Map<String, Integer> average = operations.getEachSubjectAverageMark();
		request.setAttribute("average", average);
		request.getRequestDispatcher("/staffView.jsp").forward(request, response);
//		response.sendRedirect("/staffView.jsp");
//		String jsonStr = new Gson().toJson(average);
//	    response.setContentType("application/json");
//	    response.setCharacterEncoding("UTF-8");
//	    response.getWriter().write(jsonStr);
//	    response.setStatus(200);
	}
}
