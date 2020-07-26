package jersey.api;

import java.util.Map;

public interface EmployeeApi {

	Map<String, String> getEmployeeDetails(String id);
	Map<String, String> getEmployeeAttendance(String id); 
	Map<String, String> getEmployeeSalary(String id);
}
