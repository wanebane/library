package com.rivaldy.shki.handler;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler{

//	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders header, 
			HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add("Methodnya handleMethodArgumentNotValid : "+error.getField() + ": "+ error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add("Methodnya handleMethodArgumentNotValid : "+error.getObjectName() + ": "+ error.getDefaultMessage());
		}
		
		ApiError api = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		
		return handleExceptionInternal(ex, api, header, api.getStatus(), request);
	}
	
	//thrown when request missing parameter
//	@ExceptionHandler({MissingServletRequestParameterException.class})
	public ResponseEntity<Object> handleMissingServletRequestParamater(
			MissingServletRequestParameterException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = "Methodnya handleMissingServletRequestParamater : "+ex.getParameterName() + " paramter is missing noob";
		ApiError api = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(api, new HttpHeaders(), api.getStatus());
	}
	
	//for reports the result of constraint violations
//	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<Object> handleConstraintViolation(
			ConstraintViolationException ex, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add("Methodnya handleConstraintViolation : "+violation.getRootBeanClass().getName() + " " 
					+ violation.getPropertyPath() + ": " + violation.getMessage());
		}
		
		ApiError api = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return new ResponseEntity<Object>(api, new HttpHeaders(), api.getStatus());
	}
	
	//thrown when method argument is not the expected type
//	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
			MethodArgumentTypeMismatchException ex, WebRequest request) {
		String error = "Methodnya handleMethodArgumentTypeMismatch : type "+ ex.getName() + " should be " + ex.getRequiredType().getSimpleName();
		
		ApiError api = new ApiError(HttpStatus.BAD_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST.value()), error);
		return new ResponseEntity<Object>(api, new HttpHeaders(), api.getStatus());
	}
	
//	@ExceptionHandler({NoHandlerFoundException.class})
	public ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, HttpHeaders headers, 
			HttpStatus status, WebRequest request) {
		String error = "Method nya handleNoHandlerFoundException : " + ex.getHttpMethod() + " " + ex.getRequestURL();
		
		ApiError api = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(api, new HttpHeaders(), api.getStatus());
	}
	
//	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, 
			HttpStatus status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append("Methodnya handleHttpRequestMethodNotSupported : "
				+ "method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		
		ApiError api = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), builder.toString());
		return new ResponseEntity<Object>(api, new HttpHeaders(), api.getStatus());
	}
	
//	@ExceptionHandler({HttpMediaTypeNotSupportedException.class})
	public ResponseEntity<Object> handleHttpMediaTypeNotSupported(
			HttpMediaTypeNotSupportedException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append("Methodnya handleHttpMediaTypeNotSupported : "
				+ "media type is not supported for this request. Supported methods are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));
		ApiError api = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
		return new ResponseEntity<Object>(api, new HttpHeaders(), api.getStatus());
	}
}
