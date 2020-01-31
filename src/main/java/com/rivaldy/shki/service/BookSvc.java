package com.rivaldy.shki.service;

import org.bson.Document;
import org.springframework.http.ResponseEntity;

public interface BookSvc {
	
	ResponseEntity<?> addBook(Document form);
	ResponseEntity<?> getAll();
	ResponseEntity<?> getDetail(String id);
	ResponseEntity<?> updateBook(String id, Document form);
	ResponseEntity<?> deleteBook(String id);
	ResponseEntity<?> getBookByFilter(Document form);
}
