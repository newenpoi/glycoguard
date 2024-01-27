package com.openclassrooms.medilabo.glycoguard.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Il s'agit de notre gestionnaire global d'exceptions, un mécanisme simple et flexible.
 * Franchement, c'est intéressant, la seule contrepartie est de déclarer un nombre plus ou moins important
 * d'exceptions personnalisées (ex: PatientNotFoundException).
 * Une autre alternative aurait été ResponseStatusException au niveau des services, mais avec plus de duplication de code.
 * 
 * @author newenpoi
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<Object> handlePatientNotFoundException(PatientNotFoundException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, Object> errors = new LinkedHashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String field = ((FieldError) error).getField();
			String msg = error.getDefaultMessage();
			
			errors.put(field, msg);
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
