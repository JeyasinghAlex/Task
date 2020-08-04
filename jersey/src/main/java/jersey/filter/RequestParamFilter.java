package jersey.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import jersey.utils.RestError;

@Provider
public class RequestParamFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		MultivaluedMap<String, String> pathParam = requestContext.getUriInfo().getPathParameters();
		int id = Integer.parseInt(pathParam.getFirst("id"));
		if (id % 2 == 1) {
			requestContext.abortWith(RestError.errorResponse(RestError.STATUS, RestError.ERROR_MESAGE, RestError.ERROR_CODE));
		}
	}
}
