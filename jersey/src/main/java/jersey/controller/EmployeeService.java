package jersey.controller;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jersey.api.EmployeeApiImpl;
import jersey.model.CustomAnnotation;

@Path("/employee")
public class EmployeeService {
	
	@GET
	@Path("/{id}")	
	@Produces(MediaType.APPLICATION_JSON)																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																
	public Map<String, String> getEmployee(@PathParam("id") String id) {
		return EmployeeApiImpl.getInstance().getEmployeeDetails(id);
	}
	
	@GET
	@Path("/attendance/{id}")	
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> getAttendance(@PathParam("id") String id) {
		return EmployeeApiImpl.getInstance().getEmployeeAttendance(id);
	}
																																																																																																																																																																																																																																																																																																																																																																																																																																																																			
	@GET
	@Path("/salary/{id}")	
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> getSalary(@PathParam("id") String id) {
		return EmployeeApiImpl.getInstance().getEmployeeSalary(id);
	}
}
