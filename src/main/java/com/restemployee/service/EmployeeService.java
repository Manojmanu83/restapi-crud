package com.restemployee.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.restemployee.dto.Employee;
import com.restemployee.repository.EmployeeRepository;

@Component
public class EmployeeService {

	HashMap<String, Object> map = new HashMap<String, Object>();
	@Autowired
	EmployeeRepository employeeRepository;

	public ResponseEntity<Object> add(Employee emp) {
		map.clear();
		if (!employeeRepository.existsByMobile(emp.getMobile())) {
			emp.setPercent((emp.getMath() + emp.getScience() + emp.getEng()) / 3.0);
			setResult(emp.getPercent(), emp);

			employeeRepository.save(emp);
			map.put("message", "Data Saved Success");
			map.put("statusCode", 201);
			map.put("Data", emp);
			return new ResponseEntity<Object>(map, HttpStatus.CREATED);

		} else {
			map.put("message", "Mobile Number Already Exists");
			return new ResponseEntity<Object>(map, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public ResponseEntity<Object> addAllEmployee(List<Employee> listemp) {
		map.clear();
		for (Employee emp : listemp) {
			if (!employeeRepository.existsByMobile(emp.getMobile())) {
				emp.setPercent((emp.getMath() + emp.getScience() + emp.getEng()) / 3.0);
				setResult(emp.getPercent(), emp);
			} else {
				map.put("message", "Mobile Number Already Exists");
				return new ResponseEntity<Object>(map, HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}
		employeeRepository.saveAll(listemp);
		map.put("message", "Data Saved Success");
		map.put("statusCode", 201);
		map.put("Data", listemp);
		return new ResponseEntity<Object>(map, HttpStatus.CREATED);
	}

	private void setResult(double percent, Employee emp) {
		if (emp.getMath() >= 35 && emp.getScience() >= 35 && emp.getEng() >= 35) {
			double percentage = emp.getPercent();
			if (percentage >= 35 && percentage < 50)
				emp.setResult("Third Class");
			if (percentage >= 50 && percentage < 60)
				emp.setResult("second Class");
			if (percentage >= 60 && percentage < 85)
				emp.setResult("First Class");
			if (percentage >= 85)
				emp.setResult("Distinction");
			else if (percentage < 35)
				emp.setResult("Fail");
		} else {
			emp.setResult("Fail");
		}

	}

	public ResponseEntity<Object> getEmp(int id) {
		map.clear();
		Optional<Employee> optional = employeeRepository.findById(id);
		if (!optional.isEmpty()) {
			map.put("message", "Data Fetched Success");
			map.put("Details", optional);
			return new ResponseEntity<Object>(map, HttpStatus.FOUND);
		} else {
			map.put("message", "No Data Found with id " + id);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Object> getEmp() {
		map.clear();
		List<Employee> employees = employeeRepository.findAll();
		if (!employees.isEmpty()) {
			map.put("message", "Data Fetched Success");
			map.put("Details", employees);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		} else {
			map.put("error", "No Data Found in Database");
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Object> getStudentByName(String name) {
		map.clear();
		List<Employee> students = employeeRepository.findByName(name);
		if (!students.isEmpty()) {
			map.put("message", "Data Fetched Success");
			map.put("Details", students);
			return new ResponseEntity<Object>(map, HttpStatus.FOUND);
		} else {
			map.put("message", "No Data Found with name " + name);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Object> getStudentByMobile(long mobile) {
		map.clear();
		Employee students = employeeRepository.findByMobile(mobile);
		if (students != null) {
			map.put("message", "Data Fetched Success");
			map.put("Details", students);
			return new ResponseEntity<Object>(map, HttpStatus.FOUND);
		} else {
			map.put("message", "No Data Found with mobile " + mobile);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Object> getStudentByResult(String result) {
		map.clear();
		List<Employee> s = employeeRepository.findByResult(result);
		if (!s.isEmpty()) {
			map.put("message", "Data Fetched Success");
			map.put("Data", s);
			return new ResponseEntity<Object>(map, HttpStatus.FOUND);
		} else {
			map.put("message", "No Records Found with result " + result);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}
	}

	
	public ResponseEntity<Object> deleteEmp(int id) {
		map.clear();
		if (employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
			map.put("message", "Deletion Success");
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
		else {
			map.put("error", "NO record found with id " + id);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Object> deleteAllRecords() {
		employeeRepository.deleteAll();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Deletion Success");
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

	public ResponseEntity<Object> updateEmpPatch(int id, Employee emp) {
		map.clear();
		Employee emp1 = employeeRepository.findById(id).orElse(null);
		if(emp1 == null) {
			map.put("message", "No record found");
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}
		else {
			if(emp.getName() != null)
				emp1.setName(emp.getName());
			if(emp.getMobile() != 0)
				emp1.setMobile(emp.getMobile());
			if(emp.getEng() != 0)
				emp1.setEng(emp.getEng());
			if(emp.getMath() != 0)
				emp1.setMath(emp.getMath());
			if(emp.getScience() != 0)
				emp1.setScience(emp.getScience());
			emp1.setPercent((emp1.getMath() + emp1.getScience() + emp1.getEng()) / 3.0);
			setResult(emp1.getPercent(), emp1);
			employeeRepository.save(emp1);
			map.put("message", "Updation Success");
			map.put("Details", emp1);
			return new ResponseEntity<Object>(map, HttpStatus.OK);			
		}
	}

	public ResponseEntity<Object> updateEmpPut(Employee emp) {
		map.clear();
		emp.setPercent((emp.getMath() + emp.getScience() + emp.getEng()) / 3.0);
		setResult(emp.getPercent(), emp);
		employeeRepository.save(emp);
		map.put("message", "updation Success");
		map.put("Details", emp);
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

}
