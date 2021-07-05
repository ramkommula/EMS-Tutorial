package net.javaguides.ems.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.ems.entity.EmployeeProject;
import net.javaguides.ems.service.EmployeeProjectService;

@Controller
public class EmployeeProjectController 
{
	private EmployeeProjectService employeeProjectService;
	public EmployeeProjectController(EmployeeProjectService employeeProjectService) 
	{
		super();
		this.employeeProjectService = employeeProjectService;
	}

	@GetMapping("/employeesproject")
	public String listEmployeesProject(Model model)
	{
		model.addAttribute("employeesproject", employeeProjectService.getAllEmployeeProject());
		return "employeesproject";
	}
	
	@GetMapping("/employeesproject/new")
	public String createEmployeeProjectForm(Model model)
	{
		//Create empty employeeProject object to hold create_employeeproject form data
		EmployeeProject employeeProject = new EmployeeProject();
		model.addAttribute("employeeProject", employeeProject);
		return "create_employeeproject";
	}
	
	@PostMapping("/employeesproject")
	public String saveEmployeeProject(@Valid @ModelAttribute("employeeProject") EmployeeProject employeeProject, 
			BindingResult bindingResult)
	{										
		//Here, we r using ModelAttribute to directly bind form data to the employeeProject object.
		
		if (bindingResult.hasErrors())
		{
			return "create_employeeproject";
		}
		else
		{
			employeeProjectService.saveEmployeeProject(employeeProject);
			return "redirect:/employeesproject";
		}
	}
	
	@GetMapping("/employeesproject/edit/{id}")
	public String editEmployeeProjectForm(@PathVariable Long id, Model model) //PathVariable Annotation is to get the id.
	{
		model.addAttribute("employeeProject", employeeProjectService.getEmployeeProjectById(id));
		return "edit_employeeproject";
	}
	
	@PostMapping("/employeesproject/{id}")
	public String updateEmployeeProject(@PathVariable Long id, @Valid
			@ModelAttribute("employeeProject") EmployeeProject employeeProject, BindingResult bindingResult)
	{
		
		
		// save updated employeeProject object
		if (bindingResult.hasErrors())
		{
			return "create_employeeproject";
		}
		else
		{
			// get employeeProject from database by id
			EmployeeProject existingEmployeeProject = employeeProjectService.getEmployeeProjectById(id);
			existingEmployeeProject.setId(id);
			existingEmployeeProject.setProjectName(employeeProject.getProjectName());
			existingEmployeeProject.setEmail(employeeProject.getEmail());
			existingEmployeeProject.setRole(employeeProject.getRole());
		
			employeeProjectService.updateEmployeeProject(existingEmployeeProject);
			return "redirect:/employeesproject";
		}
	}
		
		@GetMapping("/employeesproject/{id}")
		public String deleteEmployeeProject(@PathVariable Long id)
		{
			employeeProjectService.deleteEmployeeProjectById(id);
			return "redirect:/employeesproject";
		}
		
}
