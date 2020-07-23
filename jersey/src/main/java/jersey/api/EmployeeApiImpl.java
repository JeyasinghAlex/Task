package jersey.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jersey.dao.DatabaseImpl;
import jersey.model.Employee;
import jersey.utils.JsonUtil;

public class EmployeeApiImpl implements EmployeeApi {
	
	
	private static EmployeeApiImpl impl;
	
	private EmployeeApiImpl() {
		
	}
	
	public static  EmployeeApiImpl getInstance() {
		if (impl == null) {
			impl = new EmployeeApiImpl();
		}
		return impl;
	}
	
	@Override
	public Map<String, String> getEmployeeDetails(String id) {
		Map<String, String> emp = new HashMap<>();
		int empId = Integer.parseInt(id);
		List<Employee> employees = DatabaseImpl.getInstance().getEmployees();
		
		for (Employee employee : employees) {
			if (employee.getId()== empId) {				
				emp.put("id", employee.getId()+"");
				emp.put("name", employee.getName());
				emp.put("phone", employee.getPhone());
				emp.put("designation", employee.getDesignation());
				emp.put("dataOfJoining", employee.getDateOfjoining()+"");
				emp.put("salary", employee.getSalary()+"");
				emp.put("attendance", employee.getAttendance()+"");
				emp.put("experience", employee.getExperience()+"");			
			}
		}
		JsonUtil.convertJavaToJson(emp);		
		return emp;
	}
	
	@Override
	public Map<String, String> getEmployeeAttendance(String id) {
		
		Map<String, String> attendance = new HashMap<>();
		int empId = Integer.parseInt(id);
		List<Employee> employees = DatabaseImpl.getInstance().getEmployees();
		for (Employee employee : employees) {
			if (employee.getId() == empId) {
				attendance.put("attendance", employee.getAttendance()+"");
				break;
			}
		}
		JsonUtil.convertJavaToJson(attendance);
		return attendance;
	}

	@Override
	public Map<String, String> getEmployeeSalary(String id) {
		
		Map<String, String> salary = new HashMap<>();
		int empId = Integer.parseInt(id);
		List<Employee> employees = DatabaseImpl.getInstance().getEmployees();
		for (Employee employee : employees) {
			if (employee.getId() == empId) {
				salary.put("salary", employee.getSalary()+"");
				break;
			}			
		}
		JsonUtil.convertJavaToJson(salary);
		return salary;
	}
}
