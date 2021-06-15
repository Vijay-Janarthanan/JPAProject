package com.trg.boot.jpa.handler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.trg.boot.jpa.exceptions.DuplicateEmployeeException;
import com.trg.boot.jpa.exceptions.EmployeeNotFoundException;


@ControllerAdvice
public class EmployeeErrorHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = ex.getBindingResult()
				                .getFieldErrors()
				                .stream()
				                .map(x -> x.getDefaultMessage())
				                .collect(Collectors.toList());
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
		
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "Not Found");
		errors.put("Message", ex.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(DuplicateEmployeeException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundException(DuplicateEmployeeException de){
		
		Map<String,Object> errors = new LinkedHashMap<>();
		
		errors.put("Error", "DuplicateEmployee");
		errors.put("Message", de.getMessage());
		errors.put("Time", LocalDateTime.now());
		
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
		
	}

}
