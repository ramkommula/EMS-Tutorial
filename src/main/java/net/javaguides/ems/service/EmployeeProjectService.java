package net.javaguides.ems.service;

import java.util.List;


import net.javaguides.ems.entity.EmployeeProject;

public interface EmployeeProjectService 
{
	public List<EmployeeProject> getAllEmployeeProject();
	
	EmployeeProject saveEmployeeProject(EmployeeProject employeeproject);
	
	EmployeeProject getEmployeeProjectById(Long id);
	
	EmployeeProject updateEmployeeProject(EmployeeProject employeeproject);
	
	void deleteEmployeeProjectById(Long id);
}
