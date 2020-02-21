package com.myclass.controller;

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

import com.myclass.entity.Category;
import com.myclass.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@GetMapping("/index")
	public ResponseEntity<Object> index() {
		return service.findAll();
	}

	@GetMapping("")
	public ResponseEntity<Object> getCategory(@RequestParam("id") String id) {
		return service.findById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addCategory(@Valid @RequestBody Category category, BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			return service.insertCategory(category);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateCategory(@RequestParam("id") String id, @Valid @RequestBody Category category,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			return service.updateCategory(id, category);
		}

	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteCategory(@RequestParam("id") String id) {
		return service.deleteCategory(id);
	}

	@PostMapping(value = "/uploadIcon", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadIcon(@RequestParam("id") String id,
			@RequestPart("file") MultipartFile fileUploaded) {
		return service.uploadIcon(fileUploaded, id);
	}

	@GetMapping(value = "/getIcon", produces = "image/*")
	public ResponseEntity<InputStreamResource> getIcon(@RequestParam("id") String id) {
		return service.getIcon(id);
	}
}
