package com.service;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import com.api.ExcelReader;
import com.utils.RestError;
import com.utils.RestSuccess;

@Path("/v1/students")
public class FileService {

	@POST
	@Path("/input-students")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public Response readFile(@FormDataParam("file") InputStream inputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {

		JSONObject json = ExcelReader.getInstance().getResult(inputStream, fileDetail);
		if (json.isEmpty()) {
			return RestError.errorResponse(RestError.STATUS, RestError.UNSUPPORTED_MEDIA_TYPE_ERROR_STATUS
					, RestError.UNSUPPORTED_MEDIA_TYPE_STATUS_CODE);
		}
		return RestSuccess.successResponse(json);
	}
}