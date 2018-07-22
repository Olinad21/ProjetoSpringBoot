package br.com.projetos.resources.handlers;

import java.security.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.projetos.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex,HttpServletRequest request){
		StandardError error;
		error =  new StandardError(ex.getMessage(),HttpStatus.NOT_FOUND.value(),System.currentTimeMillis());		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
		
	}
}
