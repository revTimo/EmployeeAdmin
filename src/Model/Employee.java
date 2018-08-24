package Model;

import java.util.Date;

public class Employee {
	private int empID;
	private String name;
	private String email;
	private int gender;
	private String dept;
	private Date empdate;

	// setter and getter
	public int getEmpID() {
		return empID;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public int getGender() {
		return gender;
	}
	public String getDept() {
		return dept;
	}
	public Date getEmpdate() {
		return empdate;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public void setEmpdate(Date empdate) {
		this.empdate = empdate;
	}
	
}
