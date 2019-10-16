package com.akas25n.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akas25n.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
