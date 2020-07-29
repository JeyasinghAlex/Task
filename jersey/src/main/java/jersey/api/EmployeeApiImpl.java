package jersey.api;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jersey.dao.DatabaseImpl;
import jersey.model.Employee;

public class EmployeeApiImpl implements EmployeeApi {

	private static EmployeeApiImpl instance;

	private EmployeeApiImpl() {

	}

	public static EmployeeApiImpl getInstance() {
		if (instance == null) {
			instance = new EmployeeApiImpl();
		}
		return instance;
	}

	public List<Employee> getAllEmployees() {
		return DatabaseImpl.getInstance().getAll();
	}
	
	@Override
	public Map<String, String> getEmployeeDetails(long empId) {

		Map<String, String> emp = new HashMap<>();
		Employee employee = DatabaseImpl.getInstance().get(empId);
		if (employee != null) {
			emp.put("id", employee.getId() + "");
			emp.put("name", employee.getName());
			emp.put("phone", employee.getPhone());
			emp.put("designation", employee.getDesignation());
			emp.put("dataOfJoining", employee.getDateOfjoining() + "");
			emp.put("salary", employee.getSalary() + "");
			emp.put("attendance", employee.getAttendance() + "");
			emp.put("experience", employee.getExperience() + "");
		}
		return emp;
	}

	@Override
	public Map<String, String> getEmployeeAttendance(long empId) {
		
		Map<String, String> attendance = new HashMap<>();
		Employee employee = DatabaseImpl.getInstance().get(empId);
		if (employee != null) {
			attendance.put("attendance", employee.getAttendance() + "");
		}
		return attendance;
	}

	@Override
	public Map<String, String> getEmployeeSalary(long empId) {

		Map<String, String> salary = new HashMap<>();
		Employee employee = DatabaseImpl.getInstance().get(empId);
		if (employee != null) {
			salary.put("salary", employee.getSalary() + "");
		}
		return salary;
	}

	@Override
	public boolean removeEmployee(long empId) {

		return DatabaseImpl.getInstance().delete(empId);

	}

	public long createEmployee(Map<String, String> emp) {
		
		Employee employee = new Employee();
		for (Map.Entry<String, String> map : emp.entrySet()) {
			if (map.getKey().equals("name")) {
				employee.setName(map.getValue());
			} else if (map.getKey().equals("phone")) {
				employee.setPhone(map.getValue());
			} else if (map.getKey().equals("designatoin")) {
				employee.setDesignation(map.getValue());
			} else if (map.getKey().equals("dateOfjoining")) {
				employee.setDateOfjoining(LocalDate.parse(map.getValue()));
			} else if (map.getKey().equals("salary")) {
				employee.setSalary(Long.parseLong(map.getValue()));
			} else if (map.getKey().equals("attendance")) {
				employee.setAttendance(Integer.parseInt(map.getValue()));
			} else if (map.getKey().equals("experience")) {
				employee.setExperience(Integer.parseInt(map.getValue()));
			}
		}
		return DatabaseImpl.getInstance().insert(employee);
	}

	public boolean updateEmployee(long empId, Map<String, String> emp) {
		
		Employee employee = new Employee(empId);
		for (Map.Entry<String, String> map : emp.entrySet()) {
			if (map.getKey().equals("name")) {
				employee.setName(map.getValue());
			} else if (map.getKey().equals("phone")) {
				employee.setPhone(map.getValue());
			} else if (map.getKey().equals("designatoin")) {
				employee.setDesignation(map.getValue());
			} else if (map.getKey().equals("dateOfjoining")) {
				employee.setDateOfjoining(LocalDate.parse(map.getValue()));
			} else if (map.getKey().equals("salary")) {
				employee.setSalary(Long.parseLong(map.getValue()));
			} else if (map.getKey().equals("attendance")) {
				employee.setAttendance(Integer.parseInt(map.getValue()));
			} else if (map.getKey().equals("experience")) {
				employee.setExperience(Integer.parseInt(map.getValue()));
			}
		}
		return DatabaseImpl.getInstance().update(employee);
	}
}
