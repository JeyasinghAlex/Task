package com.applicaton.controller;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.applicaton.service.UserServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Path("/v1/login")
public class LoginController {

	private static final String JWT_TOKEN_KEY = "supersecret";

	@Context HttpServletResponse response;
	
	@POST
	@Path("/admin")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response loginAdmin(@FormParam("userName") String username, @FormParam("password") String password) {
			System.out.println();
		try {
			authenticate(username, password);
			String token = issueToken(username);
			System.out.println(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		NewCookie cookie = new NewCookie("name", "admin");
		return Response.ok("OK").cookie(cookie).build();
	}

	@POST
	@Path("/user")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response loginUser(@FormParam("email") String email, @FormParam("password") String password) {
		try {
			int userId = authenticate(email, password);
			String token = issueToken(email);
//			System.out.println(token);
			NewCookie tokenCookie = new NewCookie("jws", token);
			NewCookie userCookie = new NewCookie("userId", userId + "");  
			response.setHeader("name","chennai");
			return Response.status(200).
	                entity("alex").
	                header("name", "alexsparrow").build();
//			return Response.ok("OK").cookie(tokenCookie, userCookie).build();
		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
	
	private int authenticate(String email, String password) throws Exception {
		int userId = UserServiceImpl.getInstance().isUserAvailable(email, password);
		if (userId == 0) {
			throw new SecurityException("Invalid user/password");
		}
		return userId;
	}

	private String issueToken(String email) {

		try {
			Algorithm algorithm = Algorithm.HMAC256(JWT_TOKEN_KEY);
			Date expirationDate = Date.from(ZonedDateTime.now().plusHours(24).toInstant());
			Date issuedAt = Date.from(ZonedDateTime.now().toInstant());
			return JWT.create().withIssuedAt(issuedAt).withExpiresAt(expirationDate).withClaim("email", email)
					.withIssuer("jwtauth").sign(algorithm);
		} catch (JWTCreationException e) {
			e.printStackTrace();
		}
		return null;
	}
}