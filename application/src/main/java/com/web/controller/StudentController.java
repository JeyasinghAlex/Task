package com.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.web.model.Authentication;
import com.web.model.Student;
import com.web.service.StudentService;
import com.web.service.StudentServiceImpl;
import com.web.util.RestError;
import com.web.util.RestSuccess;

@Path("/v1/students")
@Authentication
public class StudentController {

	@Context
	private HttpServletRequest req;

	@GET
	@Path("/{id}/exams/results")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResult(@PathParam("id") int id) {
		StudentService service = StudentServiceImpl.getInstance();
		Student student = service.getStudent(id);
		if (student.getId() == 0) {
			return RestError.errorResponse("status", "bad request", 400);
		}
		return RestSuccess.successResponse(student);
	}

	@POST
	@Path("/{id}/exams/marks")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMark(@PathParam("id") int studentId, Map<String, Integer> marks) {
		StudentService service = StudentServiceImpl.getInstance();
		boolean isAddMark = service.addMarks(studentId, marks);
		if (!isAddMark) {
			return RestError.errorResponse();
		}
		return RestSuccess.successResponse();
	}

	@PUT
	@Path("/{id}/exams/mark")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMark(@PathParam("id") int studentId, @FormParam("subjectId") int subjectId,
			@FormParam("mark") int mark) {
		StudentService service = StudentServiceImpl.getInstance();
		boolean isUpdated = service.updateMark(studentId, subjectId, mark);
		if (!isUpdated) {
			return RestError.errorResponse();
		}
		return RestSuccess.successResponse();
	}
}
