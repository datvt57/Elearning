package com.myclass.service;

import java.util.List;

import com.myclass.entity.Result;

public interface ResultService {
	public List<Result> findAll();

	public Result findById(String id);

	public boolean addResult(Result result);

	public boolean updateResult(String id, Result result);

	public boolean deleteResult(String id);
}
