package com.trg.boot.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trg.boot.jpa.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
//public interface EmployeeRepository extends CrudRepository<Employee, Integer>{


	
}
