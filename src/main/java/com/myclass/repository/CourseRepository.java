package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
	
	@Query("select e from courses e where e.categoryid = ?1")
	List<Course> findAllByCategoryID(String categoryid);
	
	@Modifying
	@Query("update courses set image = ?1 where id= ?2")
	void setImage(String path,String id);
	
	@Query("select image from courses where id = ?1")
	String getImage(String id);
}
