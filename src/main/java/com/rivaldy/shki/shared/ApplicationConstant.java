package com.rivaldy.shki.shared;

public class ApplicationConstant {

	public static final String STATUS = "Status";
	public static final String LOOKUP = "lookup";
	public static final String BOOK = "book";
	public static final String COUNTRY = "country";
	public static final String CITY = "city";
	
	public static final String FOUND_MSG = "Data is found!";
	public static final String NOT_FOUND_MSG = "Data is not found!";
	public static final String REQUEST_NULL_MSG = "Data is null! Check Again Your Request!";
	public static final String INSERT_SUCCESS = "Insert data is success";
	public static final String INSERT_FAILED = "Insert data is failed";
	public static final String UPDATE_SUCCESS = "Update data is success";
	public static final String UPDATE_FAILED = "Update data is failed";
	public static final String DELETE_SUCCESS = "Delete data is success";
	public static final String DELETE_FAILED = "Delete data is failed";
	
	public static final String SUCCESS = "00";
	public static final String FAILED = "01";
	public static final String NOT_FOUND = "02";
	
	private static ApplicationConstant instance = null;
	
	public static synchronized ApplicationConstant getInstance() {
		if (instance == null) {
			instance = new ApplicationConstant();
		}
		return instance;
	}
	
	
}
