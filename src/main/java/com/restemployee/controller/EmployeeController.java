package com.restemployee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restemployee.dto.Employee;
import com.restemployee.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/students")
	public ResponseEntity<Object> addStudent(@RequestBody Employee emp) {
		return employeeService.add(emp);
	}
	
	@PostMapping("/students/multiple")
	public ResponseEntity<Object> addStudents(@RequestBody List<Employee> emp) {
		return employeeService.addAllEmployee(emp);
	}
	
	@GetMapping("/students")
	public ResponseEntity<Object> getStudents() {
		return employeeService.getEmp();
	}
	
	@GetMapping("/students/{id}")
	public ResponseEntity<Object> getStudent(@PathVariable int id) {
		return employeeService.getEmp(id);
	}
	
	@GetMapping("/students/name/{name}")
	public ResponseEntity<Object> getStudentByName(@PathVariable String name) {
		return employeeService.getStudentByName(name);
	}
	
	@GetMapping("/students/mobile/{mobile}")
	public ResponseEntity<Object> getStudentByMobile(@PathVariable long mobile) {
		return employeeService.getStudentByMobile(mobile);
	}
	
	@GetMapping("/students/result/{result}")
	public ResponseEntity<Object> getStudentByMobile(@PathVariable String result) {
		return employeeService.getStudentByResult(result);
	}

	
	@DeleteMapping("students/{id}")
	public ResponseEntity<Object> deleteStudent(@PathVariable int id) {
		return employeeService.deleteEmp(id);
	}
	
	@DeleteMapping("students/delete")
	public ResponseEntity<Object> deleteAllRecords() {
		return employeeService.deleteAllRecords();
	}
	
	@PatchMapping("students/update/{id}")
	public ResponseEntity<Object> updateStudentPatch(@PathVariable int id,@RequestBody Employee emp) {
		return employeeService.updateEmpPatch(id,emp);
	}
	
	@PutMapping("students/update")
	public ResponseEntity<Object> updateStudentPut(@RequestBody Employee emp) {
		return employeeService.updateEmpPut(emp);
	}
	
}
 