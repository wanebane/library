package com.rivaldy.shki.service;

import org.bson.Document;
import org.springframework.http.ResponseEntity;


public interface StatusSvc {

	ResponseEntity<?> addStatus(Document request);
	ResponseEntity<?> getAll();
	ResponseEntity<?> getDetail(String id);
	ResponseEntity<?> updateStatus(String id, Document request);
	ResponseEntity<?> deleteStatus(String id);
}
