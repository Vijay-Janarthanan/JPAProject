package com.trg.boot.jpa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trg.boot.jpa.entities.Employee;
import com.trg.boot.jpa.exceptions.DuplicateEmployeeException;
import com.trg.boot.jpa.exceptions.EmployeeNotFoundException;
import com.trg.boot.jpa.repo.EmployeeRepository;

@RestController
@RequestMapping("employees")
public class EmployeeController {

	@Autowired
	EmployeeRepository dao;
	
	@GetMapping()
	public List<Employee> getEmployees(){
		List<Employee> list = (List<Employee>) dao.findAll();
		return list;
	}
	
		
	@GetMapping("{eid}")
	public ResponseEntity<?> getEmpoyee(@PathVariable("eid") int empId) {
		Optional<Employee> opt = dao.findById(empId);
		if(opt.isPresent())
			return new ResponseEntity<Employee>(opt.get(),HttpStatus.OK);
		throw new EmployeeNotFoundException("Employee Not found with Id "+empId);
	}
	
	@PostMapping()
	public ResponseEntity<?> saveEmployee(@RequestBody Employee emp) {
		Optional<Employee> opt = dao.findById(emp.getEmpId());
		if(opt.isPresent())
			throw new DuplicateEmployeeException("Employee Id "+emp.getEmpId() +" already exists");
		dao.save(emp);
		return new ResponseEntity<String>("Employee Saved Sucessfully",HttpStatus.CREATED);
		
	}
	
	@PutMapping()
	public ResponseEntity<?> updateEmployee(@RequestBody Employee emp) {
		Optional<Employee> opt = dao.findById(emp.getEmpId());
		if(opt.isPresent())
			return new ResponseEntity<String>("Emmployee data sucessfully updated",HttpStatus.OK);
		dao.save(emp);
		throw new EmployeeNotFoundException("Employee "+emp+" does not Exist to update");
	}
	
	@DeleteMapping("{eid}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("eid") int empId) {
		Optional<Employee> opt = dao.findById(empId);
		if(opt.isPresent()) {
			dao.deleteById(empId);
			return new ResponseEntity<String>(" Deleted Sucessfully",HttpStatus.OK);	
		}
		throw new EmployeeNotFoundException("Employee with"+empId+" does not Exist to delete");
	}
//	@PatchMapping("{eid}/{sal}")
//	public ResponseEntity<?> updateSalary(@PathVariable("eid") int empId, @PathVariable("sal") float salary) {
//		
//		dao.d
//		if(e==null)
//			throw new UpdateSalaryException("" +empId);
//		e.setSalary(salary);
//		dao.update(e);
//		return new ResponseEntity<String>("Salary is Updated Sucessfully",HttpStatus.OK);
//	}
}
