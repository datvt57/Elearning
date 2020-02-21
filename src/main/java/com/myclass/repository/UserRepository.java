package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query("select e.avatar from users e where e.id= ?1")
	public String getAvatar(String id);

	@Modifying
	@Query("update users set avatar = ?1 where id= ?2")
	public void setAvatar(String uri, String id);

	@Query("select e from users e where e.email = ?1")
	public List<User> findByEmail(String email);
}
