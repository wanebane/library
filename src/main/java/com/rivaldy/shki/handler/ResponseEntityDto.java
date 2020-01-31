package com.rivaldy.shki.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;

public class ResponseEntityDto {

	private String responseCode;
	private String responseMessage;
	private Map<String, Object> data;
	
	public ResponseEntityDto() {
		// TODO Auto-generated constructor stub
	}
	public ResponseEntityDto(HttpStatus status, Map<String, Object> resultData) {
		this.responseCode = String.valueOf(status.value());
		this.responseMessage = status.getReasonPhrase();
		this.data = resultData;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
