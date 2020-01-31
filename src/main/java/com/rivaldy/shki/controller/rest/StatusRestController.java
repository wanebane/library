package com.rivaldy.shki.controller.rest;

import javax.validation.Valid;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rivaldy.shki.service.StatusSvc;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/v1/status")
public class StatusRestController {

	@Autowired
	StatusSvc statusSvc;
	
	@PostMapping
	public ResponseEntity<?> add(@Valid @RequestBody Document request) {
		return statusSvc.addStatus(request);
	}
	
	@GetMapping({"", "/"})
	public ResponseEntity<?> getAll(){
		return statusSvc.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id")String id){
		return statusSvc.getDetail(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id")String id, @Valid @RequestBody Document request){
		return statusSvc.updateStatus(id, request);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id")String id) {
		return statusSvc.deleteStatus(id);
	}
	
}
