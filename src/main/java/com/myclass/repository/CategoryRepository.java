package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
	@Query("select icon from categories where id = ?1")
	String getIcon(String id);
	
	@Modifying
	@Query("update categories set icon = ?1 where id= ?2")
	void setIcon(String path, String id);
}
