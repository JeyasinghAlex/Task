package com.service;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import com.api.ExcelReader;

@Path("/v1/students")
public class FileService {

	@POST
	@Path("/input-students")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public JSONObject readFile(@FormDataParam("file") InputStream inputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {

		return ExcelReader.getInstance().getResult(inputStream, fileDetail);
	}
}