package com.web.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.message.internal.MediaTypes;
import org.glassfish.jersey.server.ContainerRequest;

import com.web.model.Validation;

@Provider
@Validation
public class LoginFilter implements ContainerRequestFilter {

	@Context
	private UriInfo info;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		UriInfo info = requestContext.getUriInfo();
//		System.out.println(info.getAbsolutePath());

		if (requestContext instanceof ContainerRequest) {
			ContainerRequest request = (ContainerRequest) requestContext;

			if (requestContext.hasEntity()
					&& MediaTypes.typeEqual(MediaType.APPLICATION_FORM_URLENCODED_TYPE, request.getMediaType())) {
				request.bufferEntity();
				Form f = request.readEntity(Form.class);
//				System.out.println(f.asMap());
				if (f.asMap().getFirst("userName") == null || f.asMap().getFirst("password") == null) {
					requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
							.entity("User cannot access the resource.").build());
				}
			}
		}
	}
}
