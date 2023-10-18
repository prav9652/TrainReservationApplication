package com.hcl.ticketbooking.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hcl.ticketbooking.exception.TrainNotAvailableException;
import com.hcl.ticketbooking.response.MessageResponse;

/**
 * @author yash.ghawghawe
 *
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

	/**
	 * @param TrainNotAvailableException e
	 * @return ResponseEntity
	 */
	@ExceptionHandler(TrainNotAvailableException.class)
	public ResponseEntity<?> handleException(TrainNotAvailableException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Train not available for the date provided");
	}

	/**
	 * @param  MethodArgumentNotValidException ex
	 * @return ResponseEntity
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(ex.getMessage()));
	}

}
