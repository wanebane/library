package com.rivaldy.shki.service.impl;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rivaldy.shki.bo.BookBO;
import com.rivaldy.shki.domain.Book;
import com.rivaldy.shki.domain.Lookup;
import com.rivaldy.shki.domain.Status;
import com.rivaldy.shki.handler.ResponseMessage;
import com.rivaldy.shki.repository.BookRepo;
import com.rivaldy.shki.repository.LookupRepo;
import com.rivaldy.shki.repository.StatusRepo;
import com.rivaldy.shki.service.BookSvc;
import com.rivaldy.shki.shared.ApplicationConstant;

@Service
public class BookSvcImpl implements BookSvc{

	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	StatusRepo statusRepo;
	
	@Autowired
	LookupRepo lookupRepo;
	
	private ResponseEntity<?> response;
	private BookBO bookBO = BookBO.getInstance();
	private Boolean checkRequest = false;
	
	@Override
	public ResponseEntity<?> addBook(Document request) {
		checkRequest = bookBO.checkRequest(request);
		if(!checkRequest) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		Status status = statusRepo.findByTypeAndCode(ApplicationConstant.BOOK, (String) request.get("status"));
		Lookup type = lookupRepo.findByTypeAndCode(ApplicationConstant.BOOK, (String) request.get("type"));
		if(status==null||type==null) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		request.put("status", status);
		request.put("type", type);
		request.put("list", bookRepo.findAll());
		Book check = bookBO.addBook(request);
//		Book add = bookRepo.save(bookBO.addBook(request));
		Book add = bookRepo.saveAndFlush(check);
		return ResponseMessage.responseHandler(HttpStatus.OK, bookBO.responseDto(add));	
	}

	@Override
	public ResponseEntity<?> getAll() {
		List<Book> books = bookRepo.findAll();
//		Document doc = bookBO.responseDocListData(books);
		Map<String, Object> map = bookBO.responseDocListData(books);
//		response = ResponseEntity.ok(doc);
//		return response;
		return ResponseEntity.ok(map);
	}

	@Override
	public ResponseEntity<?> getDetail(String id) {
		if(id==null) return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		Book book = bookRepo.findById(id).orElse(null);
		if(book==null)
			return ResponseMessage.responseHandler(HttpStatus.NOT_FOUND);
		else
			return ResponseMessage.responseHandler(HttpStatus.OK, bookBO.responseDto(book));
	}

	@Override
	public ResponseEntity<?> updateBook(String id, Document request) {
		if(id==null) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		checkRequest = bookBO.checkRequest(request);
		if(!checkRequest) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		Status status = statusRepo.findByTypeAndCode(ApplicationConstant.BOOK, (String) request.get("book"));
		Lookup type = lookupRepo.findByTypeAndCode(ApplicationConstant.BOOK, (String) request.get("type"));
		if(status==null||type==null) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		Book book = bookRepo.findById(id).orElse(null);
		if(book==null) 
			return ResponseMessage.responseHandler(HttpStatus.NOT_FOUND);
		book.setType(type);
		book.setStatus(status);
		Book update = bookRepo.save(bookBO.updateBook(book, request));
		return ResponseMessage.responseHandler(HttpStatus.OK, bookBO.responseDto(update));
	}

	@Override
	public ResponseEntity<?> deleteBook(String id) {
		if(id==null) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		Book book = bookRepo.findById(id).orElse(null);
		if(book==null) 
			return ResponseMessage.responseHandler(HttpStatus.NOT_FOUND);
		book.setStatus(statusRepo.findByTypeAndCode(ApplicationConstant.BOOK, "inactive"));
		return ResponseMessage.responseHandler(HttpStatus.OK, null);
	}

	@Override
	public ResponseEntity<?> getBookByFilter(Document form) {
		// TODO Auto-generated method stub
		return response;
	}

}
