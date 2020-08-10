package com.web.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.web.model.Authentication;
import com.web.model.Student;
import com.web.service.ExamService;
import com.web.service.ExamServiceImpl;
import com.web.util.RestError;
import com.web.util.RestSuccess;

@Path("/v1/exams")
@Authentication
public class ExamController {

	@GET
	@Path("/results/limits")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopStudents(@QueryParam("limit") int limit) {
		ExamService service = ExamServiceImpl.getInstance();
		List<Student> students = service.getCollageTopStudents(limit);
		if (students == null) {
			return RestError.errorResponse("status", "No Records Found", 200);
		}
		return RestSuccess.sendResponse(students);
	}

	@GET
	@Path("/results")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResults() {
		ExamService service = ExamServiceImpl.getInstance();
		List<Student> students = service.getStudentsResults();
		if (students == null) {
			return RestError.errorResponse("status", "No Records Found", 200);
		}
		return RestSuccess.sendResponse(students);
	}

	@GET
	@Path("/results/{rank}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResult(@PathParam("rank") int rank) {
		ExamService service = ExamServiceImpl.getInstance();
		List<Student> students = service.getNthRankStudent(rank);
		if (students == null) {
			return RestError.errorResponse("status", "No Records Found", 200);
		}
		return RestSuccess.sendResponse(students);
	}
}
