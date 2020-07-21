package com.emulous.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emulous.dao.DatabaseImpl;
import com.emulous.service.StudentOperationImpl;


public class StudentLoginServlet extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");		
		String path=request.getContextPath();
		if (StudentOperationImpl.getInstance().isUserAvailable(username, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", password);	
			
			response.sendRedirect(path+"/studentView.jsp");
		} else {
			response.sendRedirect(path+"/index.html");
		}		
	}
}
