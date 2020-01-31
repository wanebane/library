package com.rivaldy.shki.service.impl;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rivaldy.shki.bo.LookupBO;
import com.rivaldy.shki.domain.Lookup;
import com.rivaldy.shki.domain.Status;
import com.rivaldy.shki.handler.ResponseMessage;
import com.rivaldy.shki.repository.LookupRepo;
import com.rivaldy.shki.repository.StatusRepo;
import com.rivaldy.shki.service.LookupSvc;
import com.rivaldy.shki.shared.ApplicationConstant;

@Service
public class LookupSvcImpl implements LookupSvc{

	@Autowired
	LookupRepo lookupRepo;
	
	@Autowired
	StatusRepo statusRepo;
	
	private LookupBO lookupBO = LookupBO.getInstance();
	private Boolean checkRequest = false;

	@Override
	public ResponseEntity<?> addLookup(Document request) {
		checkRequest = lookupBO.checkRequest(request);
		if(!checkRequest)
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		Status status = statusRepo.findByTypeAndCode(ApplicationConstant.LOOKUP, (String) request.get("status"));
		if(status==null) {
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		}
		request.put("status", status);
		request.put("list", lookupRepo.findAll());
		Lookup add = lookupRepo.save(lookupBO.addLookup(request));
		return ResponseMessage.responseHandler(HttpStatus.OK, lookupBO.responseDto(add));
	}

	@Override
	public ResponseEntity<?> getAll() {
		List<Lookup> lookups = lookupRepo.findAll();
		Document doc = lookupBO.responseDocListData(lookups);
		return ResponseEntity.ok(doc);
		
	}

	@Override
	public ResponseEntity<?> getDetail(String id) {
		if(id==null) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		Lookup lookup = lookupRepo.findById(id).orElse(null);
		if(lookup==null)
			return ResponseMessage.responseHandler(HttpStatus.NOT_FOUND);
		return ResponseMessage.responseHandler(HttpStatus.OK, lookupBO.responseDto(lookup));
	}

	@Override
	public ResponseEntity<?> updateLookup(String id, Document request) {
		if(id==null) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		checkRequest = lookupBO.checkRequest(request);		
		if(!checkRequest) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		Status status = statusRepo.findByTypeAndCode(ApplicationConstant.LOOKUP, (String) request.get("status"));
		if(status==null) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		Lookup lookup = lookupRepo.findById(id).orElse(null);
		if(lookup==null)
			return ResponseMessage.responseHandler(HttpStatus.NOT_FOUND);
		lookup.setStatus(status);
		Lookup update = lookupRepo.save(lookupBO.updateLookup(lookup, request));
		return ResponseMessage.responseHandler(HttpStatus.OK, lookupBO.responseDto(update));
	}

	@Override
	public ResponseEntity<?> deleteLookup(String id) {
		if(id==null) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		Lookup lookup = lookupRepo.findById(id).orElse(null);
		if(lookup==null)
			return ResponseMessage.responseHandler(HttpStatus.NOT_FOUND);
		lookup.setStatus(statusRepo.findByTypeAndCode(ApplicationConstant.LOOKUP, "inactive"));
		return ResponseMessage.responseHandler(HttpStatus.OK, null);
	}
}
