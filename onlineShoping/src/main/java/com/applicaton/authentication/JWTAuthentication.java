package com.applicaton.authentication;

import java.time.ZonedDateTime;
import java.util.Date;

import com.applicaton.service.AdminServiceImpl;
import com.applicaton.service.UserServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

public class JWTAuthentication {

	private static final String JWT_TOKEN_KEY = "supersecret";

	public static void authenticateAdmin(String userName, String password) throws Exception {
		int userId = AdminServiceImpl.getInstance().isAdminAvailable(userName, password);
		if (userId == 0) {
			throw new SecurityException("Invalid user/password");
		}
	}

	public static void authenticateUser(String email, String password) throws Exception {
		int userId = UserServiceImpl.getInstance().isUserAvailable(email, password);
		if (userId == 0) {
			throw new SecurityException("Invalid user/password");
		}
	}

	public static String issueToken(String email) {
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
