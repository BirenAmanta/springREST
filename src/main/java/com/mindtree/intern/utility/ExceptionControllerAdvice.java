package com.mindtree.intern.utility;

import java.time.LocalDate;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.intern.exception.InternException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@Autowired
	Environment enviroment;

	static final Log LOGGER=LogFactory.getLog(ExceptionControllerAdvice.class);
	@ExceptionHandler(InternException.class)
	public ResponseEntity<ErrorInfo> meetingSchedulerExceptionHandler(InternException exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(enviroment.getProperty(exception.getMessage()));
		error.setTimeStamp(LocalDate.now());
		LOGGER.error(error.getErrorMessage());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception) {
		LOGGER.error(exception.getMessage(),exception);
		ErrorInfo error = new ErrorInfo();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setErrorMessage(enviroment.getProperty("General.EXCEPTION_MESSAGE"));
		error.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class })
	public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception exception) {
		String errorMsg;
		if (exception instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException data = (MethodArgumentNotValidException) exception;
			errorMsg = data.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(","));
		} else {
			ConstraintViolationException data = (ConstraintViolationException) exception;
			errorMsg = data.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
					.collect(Collectors.joining(","));

		}
		ErrorInfo error=new ErrorInfo();
		error.setErrorMessage(errorMsg);
		error.setTimeStamp(LocalDate.now());
		LOGGER.error(error.getErrorMessage());
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorInfo>(error,HttpStatus.BAD_REQUEST);
	}
}
