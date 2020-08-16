package com.applicaton.fillter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.CookieParam;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.applicaton.model.Secured;
import com.applicaton.service.UserService;
import com.applicaton.service.UserServiceImpl;
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
	private static final String REALM = "example";

//	@CookieParam("jws") Cookie cookie;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("-------------------------------------------------");
		for (Cookie c : requestContext.getCookies().values()) 
		{
			System.out.println(c.getName());
		    if (c.getName().equals("jws")) {
		       System.out.println(c.toString());
		       System.out.println(c.getValue());
		    }
		}
//		System.out.println(cookie.toString());
//		System.out.println("i am filter");
//		System.out.println(HttpHeaders.AUTHORIZATION);
//		String authorizationHeader = requestContext.getHeaderString("jws");
//		
//		System.out.println(authorizationHeader);
//		if (!isTokenBasedAuthentication(authorizationHeader)) {
//			abortWithUnauthorized(requestContext);
//			return;
//		}
//		String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
//		System.out.println(token);
//		try {
//			validateToken(token);
//		} catch (Exception e) {
//			abortWithUnauthorized(requestContext);
//		}
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {

		return authorizationHeader != null
				&& authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext) {

		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
				.header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"").build());
	}

	private boolean validateToken(String token) {
		try {
			if (token != null) {
				Algorithm algorithm = Algorithm.HMAC256(JWT_TOKEN_KEY);
				JWTVerifier verifier = JWT.require(algorithm).withIssuer("jwtauth").build();
				DecodedJWT jwt = verifier.verify(token);

				Claim email = jwt.getClaim("email");
				UserService service = UserServiceImpl.getInstance();
				boolean isUser = service.findUserByEmail(email.asString());
				if (isUser) {
					return true;
				}
			}
		} catch (JWTVerificationException e) {
//	        LOGGER.error(e.getMessage(), e);
		}
		throw new SecurityException("Invalid user/password");
	}
}