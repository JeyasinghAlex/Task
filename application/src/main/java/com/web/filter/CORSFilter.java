package com.web.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

	@Override
	public void filter(final ContainerRequestContext request, final ContainerResponseContext response)
			throws IOException {
		response.getHeaders().add("Access-Control-Allow-Origin", "*");
		response.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		response.getHeaders().add("Access-Control-Allow-Credentials", "true");
		response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		response.getHeaders().add("Access-Control-Max-Age", "1209600");
		for (Cookie c : request.getCookies().values()) {
			if (c.getName().equals("name")) {
				response.getHeaders().add("Set-Cookie", c);
				break;
			}
		}

		CacheControl cacheControl = new CacheControl();
		cacheControl.setNoStore(true);
		cacheControl.setNoCache(true);
		cacheControl.setMustRevalidate(true);
		cacheControl.setProxyRevalidate(true);
		cacheControl.setMaxAge(0);
		cacheControl.setSMaxAge(0);
		response.getHeaders().add(HttpHeaders.CACHE_CONTROL, cacheControl.toString());
		response.getHeaders().add(HttpHeaders.EXPIRES, 0);
	}
}
