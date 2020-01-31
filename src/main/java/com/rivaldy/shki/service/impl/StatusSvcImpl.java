
package com.rivaldy.shki.service.impl;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rivaldy.shki.bo.StatusBO;
import com.rivaldy.shki.domain.Status;
import com.rivaldy.shki.handler.ResponseMessage;
import com.rivaldy.shki.repository.StatusRepo;
import com.rivaldy.shki.service.StatusSvc;

@Service
public class StatusSvcImpl implements StatusSvc{
	
	@Autowired
	StatusRepo statusRepo;
	
	private StatusBO statusBO = StatusBO.getInstance();
	private Boolean checkRequest = false;
	
	@Override
	public ResponseEntity<?> addStatus(Document request) {
		checkRequest = statusBO.checkRequest(request);
		if(!checkRequest) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		request.put("list", statusRepo.findAll());
		Status addData = statusRepo.save(statusBO.addStatus(request));
		return ResponseMessage.responseHandler(HttpStatus.OK, statusBO.responseDto(addData));
	}

	@Override
	public ResponseEntity<?> getAll() {
		List<Status> status = statusRepo.findAll();
		Map<String, Object> map = statusBO.responseMapListData(status);
		return ResponseEntity.ok(map);
	}

	@Override
	public ResponseEntity<?> getDetail(String id) {
		if(id==null) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		Status status = statusRepo.findById(id).orElse(null);
		if(status==null) 
			return ResponseMessage.responseHandler(HttpStatus.NOT_FOUND);
		return ResponseMessage.responseHandler(HttpStatus.OK, statusBO.responseDto(status));
	}

	@Override
	public ResponseEntity<?> updateStatus(String id, Document request) {
		if(id==null) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		checkRequest = statusBO.checkRequest(request);
		if(!checkRequest) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		Status status = statusRepo.findById(id).orElse(null);
		if(status==null) 
			return ResponseMessage.responseHandler(HttpStatus.NOT_FOUND);
//		Status update = statusRepo.save(statusBO.updateStatus(status, request));
		Status update = updateStatus(status);
		return ResponseMessage.responseHandler(HttpStatus.OK, statusBO.responseDto(update));
	}

	@Override
	public ResponseEntity<?> deleteStatus(String id) {
		if(id==null) 
			return ResponseMessage.responseHandler(HttpStatus.BAD_REQUEST);
		Status status = statusRepo.findById(id).orElse(null);
		if(status==null)
			return ResponseMessage.responseHandler(HttpStatus.NOT_FOUND);
//		statusRepo.deleteById(id);
		delete(status);
		return ResponseMessage.responseHandler(HttpStatus.OK, null);
	}
	
	
	
	public Status updateStatus(Status status) {
		Status update = statusRepo.saveAndFlush(status);
		return update;
	}
	
	public void update(Status status) {
		statusRepo.saveAndFlush(status);
	}
	public void delete(Status status) {
		statusRepo.delete(status);
	}

}
