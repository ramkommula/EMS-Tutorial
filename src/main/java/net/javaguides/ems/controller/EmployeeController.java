package net.javaguides.ems.controller;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.DocumentException;

import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.entity.Skill;
import net.javaguides.ems.pdfexport.EmployeePDFExporter;
import net.javaguides.ems.pdfexport.EmployeesPDFExporter;
import net.javaguides.ems.repository.SkillRepository;
import net.javaguides.ems.service.EmployeeService;

@Controller // Controller Layer holds all Spring MVC Controllers
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@Autowired
	private SkillRepository skillRepository;
	
	
	/*
	 * @GetMapping("/") //from this method will call findPaginated method -> this
	 * will return paginated Employees. public String viewHomePage(Model model) {
	 * return findPaginated(1, model); }
	 */
	
	//handler method to handle list employees and return mode and view
	@GetMapping("/employees")
	public String listEmployees(Model model)
	{
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "employees";
	}
	
	@GetMapping("/employees/new")
	public ModelAndView createEmployeeForm()
	{
		//Create empty employee object to hold create_employee form data
		Employee employee = new Employee();
		ModelAndView mav = new ModelAndView("create_employee");
		mav.addObject("employee", employee);
		
		List<Skill> skills = (List<Skill>) skillRepository.findAll();
		mav.addObject("allSkills", skills);
		return mav;
		
	}
	
	@PostMapping("/employees")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, 
			BindingResult bindingResult)
	{										
		//Here, we r using ModelAttribute to directly bind form data to the employee object.
		
		if (bindingResult.hasErrors())
		{
			return "create_employee";
		}
		else
		{
			employeeService.saveEmployee(employee);
			return "redirect:/employees";
		}
	}
	
	@GetMapping("/employees/edit/{id}")
	public ModelAndView editEmployeeForm(@PathVariable(name = "id") long id, Model model) //PathVariable Annotation is to get the id.
	{
		Employee employee = employeeService.getEmployeeById(id);
		ModelAndView mav = new ModelAndView("edit_employee");
		mav.addObject("employee", employee);
		
		List<Skill> skills = (List<Skill>) skillRepository.findAll();
		
		mav.addObject("allSkills", skills);
		
		return mav;
		
	}
	
	@PostMapping("/employees/{id}")
	public String updateEmployee(@PathVariable long id, @Valid
			@ModelAttribute("employee") Employee employee, BindingResult bindingResult)
	{
		
		
		// save updated employee object
		if (bindingResult.hasErrors())
		{
			return "create_employee";
		}
		else
		{
			// get employee from database by id
			Employee existingEmployee = employeeService.getEmployeeById(id);
			existingEmployee.setId(id);
			existingEmployee.setFirstName(employee.getFirstName());
			existingEmployee.setLastName(employee.getLastName());
			existingEmployee.setEmail(employee.getEmail());
			existingEmployee.setSkills(employee.getSkills());
			existingEmployee.setGender(employee.getGender());
			existingEmployee.setMarriage(employee.getMarriage());
			existingEmployee.setBirthday(employee.getBirthday());
			existingEmployee.setWorkat(employee.getWorkat());
			existingEmployee.setDepartment(employee.getDepartment());
			
			employeeService.updateEmployee(existingEmployee);
			return "redirect:/employees";
		}
		
	}
	
	// handler method to handle delete employee request
	
	@GetMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable long id)
	{
		employeeService.deleteEmployeeById(id);
		return "redirect:/employees";
	}
	
	/*
	 * @GetMapping("/page/{pageNo}") public String findPaginated(@PathVariable
	 * (value = "pageNo") int pageNo, Model model) { int pageSize = 5;
	 * 
	 * Page<Employee> page = employeeService.findPaginated(pageNo, pageSize);
	 * List<Employee> listEmployees = page.getContent();
	 * 
	 * model.addAttribute("currentPage", pageNo); model.addAttribute("totalPages",
	 * page.getTotalPages()); model.addAttribute("totalItems",
	 * page.getTotalElements()); model.addAttribute("listEmployees", listEmployees);
	 * 
	 * return "index";
	 * 
	 * 
	 * }
	 */
	
	@GetMapping("/employees/export/")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException
	{
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=employees_" + currentDateTime + ".pdf";
		
		response.setHeader(headerKey, headerValue);
		
		List<Employee> listEmployees = employeeService.getAllEmployees();
		
		EmployeesPDFExporter exporter = new EmployeesPDFExporter(listEmployees);
		exporter.export(response);
	}
	
	
	  @GetMapping("/employee/export/{id}") 
	  public void exportemployeeToPDF(@PathVariable(name = "id") long id,HttpServletResponse response) throws DocumentException, IOException
	  { 
		  response.setContentType("application/pdf");
	  
		  DateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd_HH:mm:ss");
		  String currentDateTime = dateFormatter.format(new Date());
	  
		  String headerKey = "Content-Disposition"; 
		  String headerValue = "attachment; filename=employees_" + currentDateTime + ".pdf";
	  
		  response.setHeader(headerKey, headerValue);
	  
		  Employee employee = employeeService.getEmployeeById(id);
	  
		  EmployeePDFExporter exporter = new EmployeePDFExporter(employee);
		  exporter.export(response); 
	  }
	 
	
	
	
	



}
