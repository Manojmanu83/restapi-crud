package com.restemployee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restemployee.dto.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	boolean existsByMobile(long mobile);

	List<Employee> findByName(String name);

	Employee findByMobile(long mobile);

	List<Employee> findByResult(String result);

}
