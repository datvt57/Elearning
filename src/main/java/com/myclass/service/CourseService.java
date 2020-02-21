package com.myclass.service;

import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.Course;

public interface CourseService {
	public List<Course> findAll();

	public List<Course> findAllByCategoryID(String categoryID);

	public Course findById(String id);

	public boolean addCourse(Course course,String userID,String roleName);

	public boolean updateCourse(String id, Course course);

	public boolean deleteCourse(String id);

	public ResponseEntity<Object> setImage(MultipartFile fileUploaded, String id);

	public ResponseEntity<InputStreamResource> getImage(String id);
}
