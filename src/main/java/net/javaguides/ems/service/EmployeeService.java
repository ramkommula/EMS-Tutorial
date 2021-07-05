package net.javaguides.ems.service;

import java.util.List;

import net.javaguides.ems.entity.Employee;

// Service Layer holds all the Service Classes, it has a business logic
public interface EmployeeService 
{
	public List<Employee> getAllEmployees();
	
	//public Employee getEmployee();

	Employee saveEmployee(Employee employee);
	
	public Employee getEmployeeById(Long id);
	
	Employee updateEmployee(Employee employee);
	
	void deleteEmployeeById(Long id);
	
	
	//Page<Employee> findPaginated(int pageNo, int pageSize);
	
	
}
