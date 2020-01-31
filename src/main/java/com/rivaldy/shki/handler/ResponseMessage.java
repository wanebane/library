package com.rivaldy.shki.handler;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseMessage {
	
	public static ResponseEntity<?> responseHandler(HttpStatus status){
		ResponseEntity<?> response = null;
		if(status.equals(HttpStatus.BAD_REQUEST)) {
			response = ResponseEntity.badRequest().header(status.getReasonPhrase(), String.valueOf(status.value())).body(new ResponseMessageHandler(status, null));
		} else if(status.equals(HttpStatus.NOT_FOUND)) {
			response = ResponseEntity.notFound().header(status.getReasonPhrase(), String.valueOf(status.value())).build();
		} else if(status.equals(HttpStatus.UNPROCESSABLE_ENTITY)) {
			response = ResponseEntity.unprocessableEntity().header(status.getReasonPhrase(), String.valueOf(status.value())).body(new ResponseMessageHandler(status, null));
		} else if(status.equals(HttpStatus.NO_CONTENT)) {
			response = ResponseEntity.noContent().header(status.getReasonPhrase(), String.valueOf(status.value())).build();
		}
		return response;
	}
	
	public static ResponseEntity<?> responseHandler(HttpStatus status, List<Object> data){
		ResponseEntity<?> response = null;
		if(status.equals(HttpStatus.BAD_REQUEST)) {
			response = ResponseEntity.badRequest().header(status.getReasonPhrase(), String.valueOf(status.value())).body(new ResponseMessageHandler(status, null));
		} else if(status.equals(HttpStatus.NOT_FOUND)) {
			response = ResponseEntity.notFound().header(status.getReasonPhrase(), String.valueOf(status.value())).build();
		} else if(status.equals(HttpStatus.UNPROCESSABLE_ENTITY)) {
			response = ResponseEntity.unprocessableEntity().header(status.getReasonPhrase(), String.valueOf(status.value())).body(new ResponseMessageHandler(status, null));
		} else if(status.equals(HttpStatus.NO_CONTENT)) {
			response = ResponseEntity.noContent().header(status.getReasonPhrase(), String.valueOf(status.value())).build();
		} else if(status.equals(HttpStatus.OK)) {
			response = ResponseEntity.ok(new ResponseMessageHandler(status, data));
		}
		return response;
	}
	
	public static ResponseEntity<?> responseHandler(HttpStatus status, Object data){
		ResponseEntity<?> response = null;
		if(status.equals(HttpStatus.BAD_REQUEST)) {
			response = ResponseEntity.badRequest().header(status.getReasonPhrase(), String.valueOf(status.value())).body(new ResponseMessageHandler(status, null));
		} else if(status.equals(HttpStatus.NOT_FOUND)) {
			response = ResponseEntity.notFound().header(status.getReasonPhrase(), String.valueOf(status.value())).build();
		} else if(status.equals(HttpStatus.UNPROCESSABLE_ENTITY)) {
			response = ResponseEntity.unprocessableEntity().header(status.getReasonPhrase(), String.valueOf(status.value())).body(new ResponseMessageHandler(status, null));
		} else if(status.equals(HttpStatus.NO_CONTENT)) {
			response = ResponseEntity.noContent().header(status.getReasonPhrase(), String.valueOf(status.value())).build();
		} else if(status.equals(HttpStatus.OK)) {
			response = ResponseEntity.ok(new ResponseMessageHandler(status, Arrays.asList(data)));
		}
		return response;
	}
}
