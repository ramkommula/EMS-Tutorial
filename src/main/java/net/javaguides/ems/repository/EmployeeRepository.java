package net.javaguides.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.ems.entity.Employee;

@Repository //Repository or DAO Layer holds all the Persistence or Database related logic. 
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
	//StudentRepository basically provides all the Crud methods(save,findAll,findAllById...etc) because it extends JpaRepository interface.
	//All the methods of JpaRepository by default provides Transactional for all it's methods
	//so, for that we don't have to add @Transactional Annotation also.
	
	//NOTE : one more important thing is that we r using Bootstrap & CSS frameworks in HTML pages to style our web pages.
	//CSS Library is very is very popular for developing response u layouts.
	//so, Here we r using bootstrap 4 css cdn links in HTML files instead of downloading & adding Bootstrap CSS files to the project. 
}	//we r using bootstrap tables, CSS classes to style the table 
	//for placing Header on browser we r copied bootstrap navbar code on Chrome & paste it in all HTML pages.
