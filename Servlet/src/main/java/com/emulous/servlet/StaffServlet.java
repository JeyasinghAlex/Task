package com.emulous.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.emulous.dao.DatabaseImpl;
import com.emulous.model.Student;
import com.emulous.service.StaffOperationHandler;
import com.emulous.service.StaffOperationImpl;
import com.google.gson.Gson;


public class StaffServlet extends HttpServlet {

	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if ("delete".equals(req.getParameter("action"))) {
			this.doDelete(req, resp);
		}else if ("update".equals(req.getParameter("action"))) {
			this.doPut(req, resp);
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String studentId = request.getParameter("id");
		String subjectId = request.getParameter("subId");
		String mark = request.getParameter("mark");
		try {
			DatabaseImpl.getInstance().updateRow(Integer.parseInt(studentId), Integer.parseInt(subjectId), Integer.parseInt(mark));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String id = request.getParameter("id");
		System.out.println(id);
		try {
			DatabaseImpl.getInstance().deleteRow(Integer.parseInt(id));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

}
