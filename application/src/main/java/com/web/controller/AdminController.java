package com.web.controller;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.web.model.Authentication;
import com.web.model.Student;
import com.web.service.AdminService;
import com.web.service.AdminServiceImpl;
import com.web.util.RestError;
import com.web.util.RestSuccess;

@Path("/v1")
@Authentication
public class AdminController {

	@POST
	@Path("/student")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudent(@FormParam("name") String name, @FormParam("department") String department) {
		Student student = new Student();
		student.setName(name);
		student.setDepartment(department);
		AdminService service = AdminServiceImpl.getInstance();
		boolean isAdd = service.addStudent(student);
		if (!isAdd) {
			return RestError.errorResponse();
		}
		return RestSuccess.successResponse();
	}

	@DELETE
	@Path("/student/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeStudent(@PathParam("id") int id) {
		AdminService service = AdminServiceImpl.getInstance();
		boolean isRemove = service.removeStudent(id);
		if (!isRemove) {
			return RestError.errorResponse();
		}
		return RestSuccess.successResponse();
	}

	@POST
	@Path("/staff")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStaff(String jsonStr) {
		AdminService service = AdminServiceImpl.getInstance();
		boolean result = service.addStaff(jsonStr);
		if (!result) {
			return RestError.errorResponse();
		}
		return RestSuccess.successResponse();
	}

	@POST
	@Path("/department")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addDepartment(String jsonStr) {
		AdminService service = AdminServiceImpl.getInstance();
		boolean result = service.addDepartment(jsonStr);
		if (!result) {
			return RestError.errorResponse();
		}
		return RestSuccess.successResponse();
	}

	@GET
	@Path("/subjects")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSubjects() {
		AdminService service = AdminServiceImpl.getInstance();
		Set<String> subjects = service.getSubjects();
		if (subjects.isEmpty()) {
			return RestError.errorResponse("status", "No Records Found", 200);
		}
		return RestSuccess.successResponse(subjects);
	}
}
