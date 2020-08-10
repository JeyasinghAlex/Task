package com.web.controller;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.web.model.Authentication;
import com.web.service.DepartmentService;
import com.web.service.DepartmentServiceImpl;
import com.web.util.RestError;
import com.web.util.RestSuccess;

@Path("v1/staffs")
@Authentication
public class StaffController {

	@GET
	@Path("/exams/results")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResults() {
		DepartmentService service = DepartmentServiceImpl.getInstance();
		Map<String, Integer> staffResults = service.getStaffsResults();
		if (staffResults == null) {
			return RestError.errorResponse("status", "No Records Found", 200);
		}
		return RestSuccess.successResponse(staffResults);
	}

}
