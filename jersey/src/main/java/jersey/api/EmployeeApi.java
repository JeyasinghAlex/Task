package jersey.api;

import java.util.Map;

public interface EmployeeApi {

	Map<String, String> getEmployeeDetails(long empId);

	Map<String, String> getEmployeeAttendance(long empId);

	Map<String, String> getEmployeeSalary(long empId);

	boolean removeEmployee(long empId);

	long createEmployee(Map<String, String> emp);
}
