package com.myclass.service.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.entity.UserCourse;
import com.myclass.repository.UserCourseRepository;
import com.myclass.service.UserCourseService;

@Service
public class UserCourseServiceImpl implements UserCourseService {

	@Autowired
	private UserCourseRepository userCourseDAO;

	@Override
	public List<UserCourse> findAll() {
		return userCourseDAO.findAll();
	}

	@Override
	public UserCourse findById(String id) {
		if (userCourseDAO.existsById(id)) {
			return userCourseDAO.findById(id).get();
		} else {
			return null;
		}
	}

	@Override
	public boolean addUserCourse(UserCourse userCourse) {
		try {
			if (userCourse != null) {
				userCourse.setId(UUID.randomUUID().toString().replace("-", ""));
				userCourseDAO.save(userCourse);
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
	public boolean updateUserCourse(String id, UserCourse userCourse) {
		UserCourse entity = findById(id);
		try {
			if (entity != null) {
				userCourse.setId(entity.getId());
				userCourseDAO.save(userCourse);
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
	public boolean deleteUserCourse(String id) {
		UserCourse entity = findById(id);
		if (entity != null) {
			userCourseDAO.delete(entity);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteUserCourseByID(String courseID) {
		try {
			userCourseDAO.deleteByCourseID(courseID);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
