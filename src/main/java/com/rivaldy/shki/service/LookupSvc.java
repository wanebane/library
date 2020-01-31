package com.rivaldy.shki.service;

import org.bson.Document;
import org.springframework.http.ResponseEntity;

public interface LookupSvc {

	ResponseEntity<?> addLookup(Document request);
	ResponseEntity<?> getAll();
	ResponseEntity<?> getDetail(String id);
	ResponseEntity<?> updateLookup(String id, Document request);
	ResponseEntity<?> deleteLookup(String id);
}
