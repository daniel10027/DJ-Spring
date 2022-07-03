package com.octogone.springboot.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.octogone.springboot.repository.EmployeeRepository;
import com.octogone.springboot.exception.RessourceNotFoundExecption;
import com.octogone.springboot.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository  employeeRepository;
	
	// Obtenir tous les employees
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	//creation de l'Api des Api
	@PostMapping("/employees")
	@CrossOrigin(origins = "http://localhost:4200")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	//Obtenir un employee par son Id sur l'Api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> 
		new RessourceNotFoundExecption("L'employé n'existe pas avec l'id " + id));
		return ResponseEntity.ok(employee);
	}
	
	//Mettre àn jour l'employe
	@PutMapping("/employees/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @RequestBody Employee employeeDetails) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> 
		new RessourceNotFoundExecption("L'employé n'existe pas avec l'id " + id));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId()); 
		Employee updateEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updateEmployee);
	}
	
	//supprimer un employee
	@DeleteMapping("/employees/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Map<String, Boolean>>deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> 
		new RessourceNotFoundExecption("L'employé n'existe pas avec l'id " + id)); 
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("delete",Boolean.TRUE);
		return ResponseEntity.ok(response);
		
		
	}
	
	

}
