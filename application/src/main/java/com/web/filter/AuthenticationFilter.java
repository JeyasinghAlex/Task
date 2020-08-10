package com.web.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.ext.Provider;

import com.web.model.Authentication;

@Provider
@Authentication
public class AuthenticationFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		requestContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		requestContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		requestContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		requestContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		requestContext.getHeaders().add("Access-Control-Max-Age", "1728000");

		boolean result = false;
		for (Cookie c : requestContext.getCookies().values()) {
			if (c.getName().equals("name")) {
				result = true;
				System.out.println("cookie name : " + c.getValue());
				break;
			}
		}
//		if (!result) {
//			requestContext.abortWith(
//					Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access the resource.").build());
//		}
	}
}