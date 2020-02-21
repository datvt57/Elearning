package com.myclass.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.User;

public interface UserService {
	public ResponseEntity<Object> findAll();

	public ResponseEntity<Object> findById(String id);

	public ResponseEntity<Object> insertUser(User user);

	public ResponseEntity<Object> updateUser(String id, User user);

	public ResponseEntity<Object> deleteUser(String id);

	public ResponseEntity<Object> uploadAvatar(MultipartFile fileUploaded, String id);

	public ResponseEntity<InputStreamResource> displayAvatar(String id);
}
