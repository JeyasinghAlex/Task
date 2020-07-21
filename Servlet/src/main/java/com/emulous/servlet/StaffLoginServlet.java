package com.emulous.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emulous.service.StaffOperationImpl;
import com.emulous.service.StudentOperationImpl;

public class StaffLoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");		
		String path=request.getContextPath();
		if (StaffOperationImpl.getInstance().isStaffAvailable(username, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", password);	
			
			response.sendRedirect(path+"/staffView.jsp");
		} else {
			response.sendRedirect(path+"/index.html");
		}
	}
}