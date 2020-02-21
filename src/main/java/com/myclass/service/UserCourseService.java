package com.myclass.service;

import java.util.List;

import com.myclass.entity.UserCourse;

public interface UserCourseService {
	public List<UserCourse> findAll();

	public UserCourse findById(String id);

	public boolean addUserCourse(UserCourse userCourse);

	public boolean updateUserCourse(String id, UserCourse userCourse);

	public boolean deleteUserCourse(String id);
	
	public boolean deleteUserCourseByID(String courseID);
}
