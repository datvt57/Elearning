package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.entity.UserCourse;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, String> {
	
	@Modifying
	@Query("delete from user_courses where course_id= ?1")
	public void deleteByCourseID(String courseID);
}
