package net.javaguides.ems.service;


import java.util.List;

import org.springframework.stereotype.Service;

import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	//Here we r write EmployeeRepository as Object
	private EmployeeRepository employeeRepository;
	//we create Constructor using only one field(EmployeeRepository) for that we don't need to use @AutoWired Annotation.
	//because, After Spring 4.3 if your class has only single constructor then there is no need to put @AutoWired.
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	

	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll(); //findAll() method returns List of employees
	}
	

	@Override
	public Employee saveEmployee(Employee employee) 
	{
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(Long id) 
	{
		return employeeRepository.findById(id).get();			
	}

	@Override
	public Employee updateEmployee(Employee employee) 
	{
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployeeById(Long id) 
	{
		employeeRepository.deleteById(id);
		
	}


	

	


	
	
}
