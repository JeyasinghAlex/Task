package com.applicaton.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.applicaton.model.User;
import com.applicaton.service.UserService;
import com.applicaton.service.UserServiceImpl;
import com.applicaton.util.RestError;
import com.applicaton.util.RestSuccess;

@Path("/v1/registration")
public class RegistrationController {

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response userRegistration(User user) {
		System.out.println(user);
		UserService service = UserServiceImpl.getInstance();
		boolean result = service.registration(user);
		if (!result) {
			return RestError.errorResponse();
		}
		return RestSuccess.successResponse();
	}

}
