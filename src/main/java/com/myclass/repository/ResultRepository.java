package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, String>{

}
