package fr.redkissifrott.abernathyPatient.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerAdvisor {

	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<CustomErrorMessage> patientNotFoundException(PatientNotFoundException ex,
			WebRequest request) {
		CustomErrorMessage message = new CustomErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<CustomErrorMessage>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PatientAlreadyExists.class)
	public ResponseEntity<CustomErrorMessage> patientAlreadyExists(PatientAlreadyExists ex, WebRequest request) {
		CustomErrorMessage message = new CustomErrorMessage(HttpStatus.CONFLICT.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<CustomErrorMessage>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> invalidParameterException(MethodArgumentNotValidException ex, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
