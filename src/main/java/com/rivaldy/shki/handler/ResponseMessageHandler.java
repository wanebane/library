package com.rivaldy.shki.handler;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ResponseMessageHandler {
	
	private Integer responseCode;
	private String responseMessage;
	private List<Object> dataList;
	
	public ResponseMessageHandler(Integer status, String message, Object list) {
		super();
		this.responseCode = status;
		this.responseMessage = message;
		this.dataList = list==null ? null : Arrays.asList(list);
	}
	
	public ResponseMessageHandler(HttpStatus status, List<Object> list) {
		super();
		this.responseCode = status.value();
		this.responseMessage = status.getReasonPhrase();
		this.dataList = list==null ? null : list;
	}
		
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public List<Object> getData() {
		return dataList;
	}

	public void setData(List<Object> data) {
		this.dataList = data;
	}
	
	
}
