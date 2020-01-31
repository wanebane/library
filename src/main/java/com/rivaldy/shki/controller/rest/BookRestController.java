package com.rivaldy.shki.controller.rest;

import javax.validation.Valid;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rivaldy.shki.service.BookSvc;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/v1/book")
public class BookRestController {
	
	@Autowired
	BookSvc bookSvc;
	
	@GetMapping({"","/"})
	public ResponseEntity<?> getAll(){
		return bookSvc.getAll();
	}
	
	@PostMapping
	public ResponseEntity<?> add(@Valid @RequestBody Document request) {
		return bookSvc.addBook(request);
	}
}
