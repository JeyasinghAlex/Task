package jersey.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement
public class Employee {

	private long id;
	private String name;
	private String phone;
	private String designation;
	private LocalDate dateOfjoining;
	private long salary;
	private int attendance;
	private int experience;

	public Employee(long id, String name, String phone, String designation, LocalDate dateOfjoining, long salary,
			int attendance, int experience) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.designation = designation;
		this.dateOfjoining = dateOfjoining;
		this.salary = salary;
		this.attendance = attendance;
		this.experience = experience;
	}

	public Employee(long id) {
		this.id = id;
	}

	public Employee() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public LocalDate getDateOfjoining() {
		return dateOfjoining;
	}

	public void setDateOfjoining(LocalDate dateOfjoining) {
		this.dateOfjoining = dateOfjoining;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public int getAttendance() {
		return attendance;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + id);
		return result;
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (this.getClass() != obj.getClass())
//			return false;
//		Employee employee = (Employee) obj;
//		if (this.id != employee.id)
//			return false;
//		return true;
//	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Employee) {
			Employee employee = (Employee) obj;
			if (this.id == employee.getId()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
