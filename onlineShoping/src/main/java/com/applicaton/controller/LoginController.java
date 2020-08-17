package com.applicaton.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.applicaton.authentication.JWTAuthentication;

@Path("/v1/login")
public class LoginController {

	@POST
	@Path("/admin")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response loginAdmin(@FormParam("userName") String userName, @FormParam("password") String password) {
		System.out.println();
		try {
			JWTAuthentication.authenticateAdmin(userName, password);
			String token = JWTAuthentication.issueToken(userName);
			return Response.ok(token).build();
		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}

	@POST
	@Path("/user")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response loginUser(@FormParam("email") String email, @FormParam("password") String password) {
		try {
			JWTAuthentication.authenticateUser(email, password);
			String token = JWTAuthentication.issueToken(email);
			return Response.ok(token).build();
		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
}