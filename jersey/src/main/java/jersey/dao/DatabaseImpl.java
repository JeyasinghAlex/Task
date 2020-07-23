package jersey.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jersey.model.Employee;

public class DatabaseImpl {

	
	private static DatabaseImpl  impl;
	
	private DatabaseImpl() {
		
	}
	
	public static DatabaseImpl getInstance() {
		if (impl == null) {
			impl = new DatabaseImpl();
		}
		return impl;
	}
	
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();
		Employee  emp1 = new Employee(1, "abc", "123456789",  "trinee"
				, LocalDate.parse("2014-02-14"),10000, 92, 1);
		
		Employee  emp2 = new Employee(2, "lmn", "987654321",  "developer"
				, LocalDate.parse("2013-02-14"),20000, 90, 2);
		

		Employee  emp3 = new Employee(3, "xyz", "5131151140",  "tester"
				, LocalDate.parse("2012-02-14"),15000, 98, 3);
		

		Employee  emp4 = new Employee(4, "pqr", "151230406",  "manager"
				, LocalDate.parse("2010-02-14"),30000, 90, 6);
		

		Employee  emp5 = new Employee(5, "jkl", "35797425",  "content writer"
				, LocalDate.parse("2013-02-14"),15000, 88, 2);
		
		employees.add(emp1);
		employees.add(emp2);
		employees.add(emp3);
		employees.add(emp4);
		employees.add(emp5);
		
		return employees;
	}
}
