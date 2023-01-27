package br.com.cruz.vita.usuario.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity handlerException(Exception e) {
		
		return new ResponseEntity("ConstraintViolationException", HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity tratarNullPointerException(Exception e) {
		
		return new ResponseEntity("NullPointerException", HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity tratarErroValidacao(Exception e) {
		
		return new ResponseEntity("Usuario já exisitente em nosso sistema", HttpStatus.UNAUTHORIZED);
	}
}
