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

import com.myclass.entity.UserCourse;
import com.myclass.service.UserCourseService;

@RestController
@RequestMapping("/api/user-course")
public class UserCourseController {

	@Autowired
	private UserCourseService service;

	@GetMapping("/index")
	public ResponseEntity<Object> index() {
		List<UserCourse> list = service.findAll();
		if (!list.isEmpty()) {
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("")
	public ResponseEntity<Object> getUserCourse(@RequestParam("id") String id) {
		UserCourse userCourse = service.findById(id);
		if (userCourse != null) {
			return new ResponseEntity<Object>(userCourse, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addUserCourse(@Valid @RequestBody UserCourse userCourse, BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			if (service.addUserCourse(userCourse)) {
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateUserCourse(@RequestParam("id") String id,
			@Valid @RequestBody UserCourse userCourse, BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			if (service.updateUserCourse(id, userCourse)) {
				return new ResponseEntity<Object>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteUserCourse(@RequestParam("id") String id) {
		if (service.deleteUserCourse(id)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
