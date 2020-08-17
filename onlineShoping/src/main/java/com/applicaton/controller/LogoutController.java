package com.applicaton.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/logout")
public class LogoutController {

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public Response logout() {
		return Response.ok("OK - No session").build();
	}
}
