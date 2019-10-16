package com.akas25n.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akas25n.exceptions.ResourceNotFoundException;
import com.akas25n.model.Employee;
import com.akas25n.repository.EmployeeRepository;

@RestController
@RequestMapping(path="/")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@RequestMapping("/employees")
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	@RequestMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
        throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
        		.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }
	
	@RequestMapping(value = "/employees", method = RequestMethod.POST)
	public Employee createNewEmployee(@Valid @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee newEmployee, @PathVariable(value = "id") long employeeId) throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		employee.setFirstName(newEmployee.getFirstName());
		employee.setLastName(newEmployee.getLastName());
		employee.setEmail(newEmployee.getEmail());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok().body(updatedEmployee);
	}
	
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	

	//--------------------------------
	@RequestMapping("/greeting")
	public String greeting(){
		
		return "Hi There !";
	}
	
	

}
