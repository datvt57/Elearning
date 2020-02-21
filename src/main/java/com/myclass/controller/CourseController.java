package com.myclass.controller;

import java.util.List;

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

import com.myclass.entity.Course;
import com.myclass.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	private CourseService service;

	@GetMapping("/index")
	public ResponseEntity<Object> index(@RequestParam("id") String categoryID) {
		List<Course> list = service.findAllByCategoryID(categoryID);
		if (!list.isEmpty()) {
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("")
	public ResponseEntity<Object> getCourse(@RequestParam("id") String id) {
		Course entity = service.findById(id);
		if (entity != null) {
			return new ResponseEntity<Object>(entity, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addCourse(@RequestParam("userid") String userID,
			@RequestParam("role") String roleName, @Valid @RequestBody Course course, BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			if (service.addCourse(course, userID, roleName)) {
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateCourse(@RequestParam("id") String id, @Valid @RequestBody Course course,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			if (service.updateCourse(id, course)) {
				return new ResponseEntity<Object>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteCourse(@RequestParam("id") String id) {
		if (service.deleteCourse(id)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadImage(@RequestParam("id") String id,
			@RequestPart("file") MultipartFile fileUploaded) {
		return service.setImage(fileUploaded, id);
	}

	@GetMapping(value = "/getImage", produces = "image/*")
	public ResponseEntity<InputStreamResource> getImage(@RequestParam("id") String id) {
		return service.getImage(id);
	}
}
