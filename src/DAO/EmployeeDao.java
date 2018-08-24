package DAO;

import java.sql.*;
import java.util.*;

import Model.Employee;
import util.Database;

public class EmployeeDao {
	private Connection connection;
	
	public EmployeeDao () {
		connection = Database.getConnection();
	}

	// add employee
	public boolean addEmployee(Employee employee) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into employees values (?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, employee.getEmpID());
			preparedStatement.setString(2, employee.getName());
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setInt(4, employee.getGender());
			preparedStatement.setString(5, employee.getDept());
			if (employee.getEmpdate() == null) {
				preparedStatement.setDate(6, null);
			} else {
				preparedStatement.setDate(6, new java.sql.Date(employee.getEmpdate().getTime()));
			}
			if (preparedStatement.executeUpdate() != 1) {
				return false;
			};
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from employees order by empid desc");
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmpID(rs.getInt("empid"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				employee.setGender(rs.getInt("gender"));
				employee.setDept(rs.getString("dept"));
				employee.setEmpdate(rs.getDate("empdate"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}
}
