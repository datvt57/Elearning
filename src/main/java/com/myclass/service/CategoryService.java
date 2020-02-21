package com.myclass.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.Category;

public interface CategoryService {
	public ResponseEntity<Object> findAll();

	public ResponseEntity<Object> findById(String id);

	public ResponseEntity<Object> insertCategory(Category category);

	public ResponseEntity<Object> updateCategory(String id, Category category);

	public ResponseEntity<Object> deleteCategory(String id);
	
	public ResponseEntity<Object> uploadIcon(MultipartFile fileUploaded, String id);
	
	public ResponseEntity<InputStreamResource> getIcon(String id);
}
