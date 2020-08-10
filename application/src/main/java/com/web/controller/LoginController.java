package com.web.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.web.model.Validation;
import com.web.service.AdminService;
import com.web.service.AdminServiceImpl;
import com.web.util.RestError;

@Path("/v1/login")
public class LoginController {

	@POST
	@Path("/admin")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	@Validation
	public Response loginAdmin(@FormParam("userName") String name, @FormParam("password") String password) {
		AdminService service = AdminServiceImpl.getInstance();
		boolean result = service.isAdminAvailable(name, password);
		if (!result) {
			return RestError.errorResponse();
		}
		NewCookie cookie = new NewCookie("name", "admin");
		return Response.ok("success").cookie(cookie).build();
	}

	@POST
	@Path("/staff")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	@Validation
	public Response loginStaff(@FormParam("userName") String name, @FormParam("password") String password) {
		AdminService service = AdminServiceImpl.getInstance();
		boolean result = service.isStaffAvailable(name, password);
		if (!result) {
			return RestError.errorResponse();
		}
		NewCookie cookie = new NewCookie("name", "staff");
		return Response.ok("success").cookie(cookie).build();
	}

	@POST
	@Path("/student")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	@Validation
	public Response loginStudent(@FormParam("userName") String name, @FormParam("password") String password) {
		AdminService service = AdminServiceImpl.getInstance();
		boolean result = service.isStudentAvailable(name, password);
		if (!result) {
			return RestError.errorResponse();
		}
		NewCookie cookie = new NewCookie("name", "student");
		return Response.ok("success").cookie(cookie).build();
	}
}
