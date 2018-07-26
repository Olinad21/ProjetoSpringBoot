package br.com.projetos.resources.handlers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.projetos.exceptions.*;


@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex,HttpServletRequest request){
		StandardError error;
		error =  new StandardError(ex.getMessage(),HttpStatus.NOT_FOUND.value(),System.currentTimeMillis());		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
				
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> ConstraintViolation(ConstraintViolationException ex,HttpServletRequest request){
		StandardError error;
		error =  new StandardError(ex.getMessage(),HttpStatus.BAD_REQUEST.value(),System.currentTimeMillis());		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
		
	}
}
