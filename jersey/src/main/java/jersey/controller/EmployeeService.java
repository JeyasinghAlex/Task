package jersey.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import jersey.api.EmployeeApiImpl;
import jersey.model.Employee;

@Path("/v1/employees")
public class EmployeeService {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployee() {
		return EmployeeApiImpl.getInstance().getAllEmployees();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> getEmployee(@PathParam("id") long empId) {
		return EmployeeApiImpl.getInstance().getEmployeeDetails(empId);
	}

	@GET
	@Path("/attendance/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> getAttendance(@PathParam("id") long empId) {
		return EmployeeApiImpl.getInstance().getEmployeeAttendance(empId);
	}

	@GET
	@Path("/salary/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> getSalary(@PathParam("id") long empId) {
		return EmployeeApiImpl.getInstance().getEmployeeSalary(empId);
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public long createEmoloyee(Map<String, String> emp) {
		return EmployeeApiImpl.getInstance().createEmployee(emp);
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean update(@PathParam("id") long empId, Map<String, String> emp) {
		return EmployeeApiImpl.getInstance().updateEmployee(empId, emp);
	}

	@DELETE
	@Path("/{id}")
	public boolean removeEmployee(@PathParam("id") long empId) {
		return EmployeeApiImpl.getInstance().removeEmployee(empId);
	}
}
