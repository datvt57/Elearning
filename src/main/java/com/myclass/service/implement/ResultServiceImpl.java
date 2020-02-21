package com.myclass.service.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.entity.Result;
import com.myclass.repository.ResultRepository;
import com.myclass.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	private ResultRepository resultDAO;

	@Override
	public List<Result> findAll() {
		return resultDAO.findAll();
	}

	@Override
	public Result findById(String id) {
		if (resultDAO.existsById(id)) {
			return resultDAO.findById(id).get();
		} else {
			return null;
		}
	}

	@Override
	public boolean addResult(Result result) {
		try {
			if (result != null) {
				result.setId(UUID.randomUUID().toString().replace("-", ""));
				resultDAO.save(result);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateResult(String id, Result result) {
		Result entity = findById(id);
		try {
			if (entity != null) {
				result.setId(entity.getId());
				resultDAO.save(result);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteResult(String id) {
		Result entity = findById(id);
		if (entity != null) {
			resultDAO.delete(entity);
			return true;
		} else {
			return false;
		}
	}

}
