package net.javaguides.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"net.javaguides.ems.controller","net.javaguides.ems.pdfexport","net.javaguides.ems.entity","net.javaguides.ems.repository","net.javaguides.ems.service"})
@EnableJpaRepositories("net.javaguides.ems.repository")
@EntityScan("net.javaguides.ems.entity")
public class EmployeeManagmentSystemmApplication /*implements CommandLineRunner*/ {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagmentSystemmApplication.class, args);
	}

	
	/*
	 * @Autowired private EmployeeRepositoryProject employeeRepositoryProject;
	 * 
	 * @Override public void run(String... args) throws Exception {
	 * 
	 * EmployeeProject employee1 = new
	 * EmployeeProject("Kumar","vikram@gmail.com","hsa");
	 * employeeRepositoryProject.save(employee1);
	 * 
	 * EmployeeProject employee2 = new
	 * EmployeeProject("Varma","Ashok@gmail.com","sac");
	 * employeeRepositoryProject.save(employee2);
	 * 
	 * EmployeeProject employee3 = new
	 * EmployeeProject("Kiran","kiran@gmail.com","gxsx");
	 * employeeRepositoryProject.save(employee3); }
	 */
	 
}
