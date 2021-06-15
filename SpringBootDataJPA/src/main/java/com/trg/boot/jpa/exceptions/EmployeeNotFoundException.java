package com.trg.boot.jpa.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
	
	public EmployeeNotFoundException(String msg){
		super(msg);
	}

}
