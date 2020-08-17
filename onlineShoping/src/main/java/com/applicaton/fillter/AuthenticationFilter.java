package com.applicaton.fillter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.applicaton.model.Secured;
import com.applicaton.model.User;
import com.applicaton.service.UserService;
import com.applicaton.service.UserServiceImpl;
import com.applicaton.util.UserHandler;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	private static final String JWT_TOKEN_KEY = "supersecret";
	private static final String AUTHENTICATION_SCHEME = "Bearer";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (!isTokenBasedAuthentication(authorizationHeader)) {
			abortWithUnauthorized(requestContext);
			return;
		}
		String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

		try {
			validateToken(token);
		} catch (Exception e) {
			abortWithUnauthorized(requestContext);
		}
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {
		return authorizationHeader != null
				&& authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext) {
		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
				.header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME).build());
	}

	private boolean validateToken(String token) {
		try {
			if (token != null) {
				Algorithm algorithm = Algorithm.HMAC256(JWT_TOKEN_KEY);
				JWTVerifier verifier = JWT.require(algorithm).withIssuer("jwtauth").build();
				DecodedJWT jwt = verifier.verify(token);
				Claim email = jwt.getClaim("email");
				UserService service = UserServiceImpl.getInstance();
				User user = service.findUserByEmail(email.asString());
				if (user != null) {
					UserHandler.setUser(user);
					return true;
				}
			}
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		}
		throw new SecurityException("Invalid user/password");
	}
}