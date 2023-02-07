package br.com.cruz.vita.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 * 
 * @name : Classe para erros possuveis 
 * 
 * 
 */

@ControllerAdvice
public class ApplicationExcerptionHandler  extends ResponseEntityExceptionHandler   {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException (Exception ae) {
		return new ResponseEntity("Usuario ja existe" , HttpStatus.BAD_REQUEST);
	}
	
}
