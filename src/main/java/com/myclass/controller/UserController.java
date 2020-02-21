package com.myclass.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.User;
import com.myclass.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/index")
	public ResponseEntity<Object> index() {
		return service.findAll();
	}

	@GetMapping("")
	public ResponseEntity<Object> getUser(@RequestParam("id") String id) {
		return service.findById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user, BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			return service.insertUser(user);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateUser(@RequestParam("id") String id, @Valid @RequestBody User user,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			return service.updateUser(id, user);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteUser(@RequestParam("id") String id) {
		return service.deleteUser(id);
	}

	@PostMapping(value = "/uploadAvatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadAvatar(@RequestParam("id") String id,
			@RequestPart("file") MultipartFile fileUploaded) throws IOException {
		return service.uploadAvatar(fileUploaded, id);
	}

	@GetMapping(value = "/getAvatar", produces = "image/*")
	public ResponseEntity<InputStreamResource> getAvatar(@RequestParam("id") String id) {
		return service.displayAvatar(id);
	}
}
