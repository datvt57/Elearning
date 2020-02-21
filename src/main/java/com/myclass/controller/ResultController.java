package com.myclass.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.Result;
import com.myclass.service.ResultService;

@RestController
@RequestMapping("/api/result")
public class ResultController {

	@Autowired
	private ResultService service;

	@GetMapping("/index")
	public ResponseEntity<Object> index() {
		List<Result> list = service.findAll();
		if (!list.isEmpty()) {
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("")
	public ResponseEntity<Object> getResult(@RequestParam("id") String id) {
		Result entity = service.findById(id);
		if (entity != null) {
			return new ResponseEntity<Object>(entity, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addResult(@Valid @RequestBody Result result, BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			if (service.addResult(result)) {
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateResult(@RequestParam("id") String id, @Valid @RequestBody Result result,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			if (service.updateResult(id, result)) {
				return new ResponseEntity<Object>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteResult(@RequestParam("id") String id) {
		if (service.deleteResult(id)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
