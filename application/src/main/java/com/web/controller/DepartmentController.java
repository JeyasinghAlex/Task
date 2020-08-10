package com.web.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.web.model.Authentication;
import com.web.model.Student;
import com.web.service.DepartmentService;
import com.web.service.DepartmentServiceImpl;
import com.web.util.RestError;
import com.web.util.RestSuccess;

@Path("/v1/departments")
@Authentication
public class DepartmentController {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDepartments() {
		DepartmentService service = DepartmentServiceImpl.getInstance();
		Set<String> subjects = service.getDepartments();
		if (subjects.isEmpty()) {
			return RestError.errorResponse("status", "No Records Found", 200);
		}
		return RestSuccess.successResponse(subjects);
	}

	@GET
	@Path("/subjects/exams/results")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSubjectAverage() {
		DepartmentService service = DepartmentServiceImpl.getInstance();
		Map<String, Integer> average = service.getSubjectsAverage();
		if (average.isEmpty()) {
			return RestError.errorResponse("status", "No Records Found", 200);
		}
		return RestSuccess.successResponse(average);
	}

	@GET
	@Path("/subjects/exams/results/mark")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSubjectHighestMark() {
		DepartmentService service = DepartmentServiceImpl.getInstance();
		List<List<String>> highestMark = service.getSubjectsHighestMark();
		if (highestMark.isEmpty()) {
			return RestError.errorResponse("status", "No Records Found", 200);
		}
		return RestSuccess.successResponse(highestMark);
	}

	@GET
	@Path("/exams/results")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResults() {
		DepartmentService service = DepartmentServiceImpl.getInstance();
		Map<String, Integer> deptResults = service.getDepartmentsResults();
		if (deptResults.isEmpty()) {
			return RestError.errorResponse("status", "No Records Found", 200);
		}
		return RestSuccess.successResponse(deptResults);
	}

	@GET
	@Path("/exams/results/limits")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopRank(@QueryParam("limit") int limit) {
		DepartmentService service = DepartmentServiceImpl.getInstance();
		List<Student> topStudents = service.getDepartmentsTopStudents(limit);
		if (topStudents.isEmpty()) {
			return RestError.errorResponse("status", "No Records Found", 200);
		}
		return RestSuccess.sendResponse(topStudents);
	}

	@GET
	@Path("/subjects")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSubject(@QueryParam("studentId") int studentId) {
		DepartmentService service = DepartmentServiceImpl.getInstance();
		Set<String> subjects = service.getSubject(studentId);
		if (subjects.isEmpty()) {
			return RestError.errorResponse("status", "No Records Found", 200);
		}
		return RestSuccess.successResponse(subjects);
	}
}