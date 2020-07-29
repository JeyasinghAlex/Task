package jersey.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jersey.model.Employee;

public class DatabaseImpl {

	private static DatabaseImpl instance;
	private static List<Employee> employees = new ArrayList<>();

	private DatabaseImpl() {

	}

	public static DatabaseImpl getInstance() {
		if (instance == null) {
			instance = new DatabaseImpl();
		}
		return instance;
	}

	static {

		employees.add(new Employee(1, "abc", "123456789", "trinee", LocalDate.parse("2014-02-14"), 10000, 92, 1));

		employees.add(new Employee(2, "lmn", "987654321", "developer", LocalDate.parse("2013-02-14"), 20000, 90, 2));

		employees.add(new Employee(3, "xyz", "5131151140", "tester", LocalDate.parse("2012-02-14"), 15000, 98, 3));

		employees.add(new Employee(4, "pqr", "151230406", "manager", LocalDate.parse("2010-02-14"), 30000, 90, 6));

		employees.add(new Employee(5, "jkl", "35797425", "content writer", LocalDate.parse("2013-02-14"), 15000, 88, 2));
		
	}
	
	public List<Employee> getAll() {
		return new ArrayList<Employee>(employees);
	}

	public Employee get(long empId) {
		Employee employeeToFind = new Employee(empId);
		int index = employees.indexOf(employeeToFind);
		if (index >= 0) {
			return employees.get(index);
		}
		return null;
	}

	public long insert(Employee employee) {
		long newId = employees.size() + 1;
		employee.setId(newId);
		employees.add(employee);
		return newId;
	}

	public boolean update(Employee employee) {
		int index = employees.indexOf(employee);
		if (index >= 0) {
			employees.set(index, employee);
			return true;
		}
		return false;
	}

	public boolean delete(long empId) {
		Employee employeeToFind = new Employee(empId);
		int index = employees.indexOf(employeeToFind);
		if (index >= 0) {
			employees.remove(index);
			return true;
		}
		return false;
	}

}
