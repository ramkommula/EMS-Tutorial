package net.javaguides.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import net.javaguides.ems.entity.EmployeeProject;
import net.javaguides.ems.repository.EmployeeRepositoryProject;

@Service
public class EmployeeProjectServiceImpl implements EmployeeProjectService
{
	//EmployeeProject Logic implementations
		private EmployeeRepositoryProject employeeRepositoryProject;
		
		  public EmployeeProjectServiceImpl(EmployeeRepositoryProject employeeRepositoryProject) 
		  { 
			  super(); 
			  this.employeeRepositoryProject = employeeRepositoryProject; 
		  }
		 
		@Override
		public List<EmployeeProject> getAllEmployeeProject() {
			return employeeRepositoryProject.findAll();
		}


		@Override
		public EmployeeProject saveEmployeeProject(EmployeeProject employeeproject) 
		{
			return employeeRepositoryProject.save(employeeproject);
		}


		@Override
		public EmployeeProject getEmployeeProjectById(Long id) 
		{
			return employeeRepositoryProject.findById(id).get();
		}


		@Override
		public EmployeeProject updateEmployeeProject(EmployeeProject employeeproject) 
		{
			return employeeRepositoryProject.save(employeeproject);
		}


		@Override
		public void deleteEmployeeProjectById(Long id) 
		{
			employeeRepositoryProject.deleteById(id);
			
		}
		
}
