package com.trg.boot.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.trg.boot.jpa.entities.Employee;
import com.trg.boot.jpa.repo.EmployeeRepository;

//@Component
public class DBInit implements CommandLineRunner{

	@Autowired
	EmployeeRepository repo;
	
	@Override
	public void run(String... args) throws Exception {
		
		repo.save(new Employee(100, "Vijay",21828));
		repo.save(new Employee(200, "Ram",22000));
		repo.save(new Employee(300, "Naveen",25000));
		repo.save(new Employee(400, "Nethaji",16000));
		
	}

}
