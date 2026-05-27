package com.patient.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handlePatientNotFound(PatientNotFoundException exception)
	{
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("timestamp", LocalDateTime.now());
		map.put("status", 404);
		map.put("error", "Not Found");
		map.put("message","Patient with ID "+exception.getMessage());
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {

	    
	    Map<String, String> fieldErrors = new HashMap<>();
	    ex.getBindingResult().getFieldErrors()
	      .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

	    Map<String, Object> response = new HashMap<>();
	    response.put("timestamp", LocalDateTime.now());
	    response.put("status", 400);
	    response.put("error", "BAD REQUEST");
	    response.put("messages", fieldErrors);

	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", 500);
        error.put("error", "INTERNAL SERVER ERROR");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
}
