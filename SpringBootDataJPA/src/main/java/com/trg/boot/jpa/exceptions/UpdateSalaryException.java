package com.trg.boot.jpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Employe Does not Exist")
public class UpdateSalaryException extends RuntimeException{

	public UpdateSalaryException(String message) {
		super(message);
		}
	

}
