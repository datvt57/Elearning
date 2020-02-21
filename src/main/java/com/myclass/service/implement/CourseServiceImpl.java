package com.myclass.service.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.Course;
import com.myclass.entity.UserCourse;
import com.myclass.repository.CourseRepository;
import com.myclass.service.CourseService;
import com.myclass.service.UserCourseService;

@Service
public class CourseServiceImpl implements CourseService {

	private final String SRC = "E:/upload/image/course/";

	@Autowired
	private CourseRepository courseDAO;

	@Autowired
	private UserCourseService service;

	@Override
	public List<Course> findAll() {
		return courseDAO.findAll();
	}

	@Override
	public Course findById(String id) {
		if (courseDAO.existsById(id)) {
			return courseDAO.findById(id).get();
		} else {
			return null;
		}
	}

	@Override
	public boolean addCourse(Course course, String userID, String roleName) {
		UserCourse userCourse = new UserCourse();
		try {
			if (course != null) {
				course.setId(UUID.randomUUID().toString().replace("-", ""));
				courseDAO.save(course);
				userCourse.setCourseid(course.getId());
				userCourse.setUserid(userID);
				userCourse.setRoleid(roleName);
				service.addUserCourse(userCourse);
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
	public boolean updateCourse(String id, Course course) {
		Course entity = findById(id);
		try {
			if (entity != null) {
				course.setId(entity.getId());
				courseDAO.save(course);
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
	@Transactional
	public boolean deleteCourse(String id) {
		Course entity = findById(id);
		if (entity != null) {
			if (service.deleteUserCourseByID(id)) {
				courseDAO.delete(entity);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public List<Course> findAllByCategoryID(String categoryID) {
		return courseDAO.findAllByCategoryID(categoryID);
	}

	@Override
	@Transactional
	public ResponseEntity<Object> setImage(MultipartFile fileUploaded, String id) {
		if (fileUploaded != null || id != null) {
			String type = fileUploaded.getContentType().replace("image/", "");
			File data = new File(SRC + id + "." + type);
			String path = id + "." + type;
			try {
				data.createNewFile();
				FileOutputStream fos = new FileOutputStream(data);
				fos.write(fileUploaded.getBytes());
				courseDAO.setImage(path, id);
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Object>("Lỗi không xác định!", HttpStatus.EXPECTATION_FAILED);
			}
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Thông tin không đầy đủ!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<InputStreamResource> getImage(String id) {
		if (id != null) {
			String pathDefault = "777.png";
			String path = courseDAO.getImage(id);
			File data = null;
			if (path != null) {
				data = new File(SRC + path);
			} else {
				data = new File(SRC + pathDefault);
			}
			if (data.exists()) {
				try {
					InputStream is = new FileInputStream(data);
					return ResponseEntity.ok().contentLength(data.length()).contentType(MediaType.IMAGE_JPEG)
							.contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(is));
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<InputStreamResource>(HttpStatus.EXPECTATION_FAILED);
				}
			} else {
				return new ResponseEntity<InputStreamResource>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<InputStreamResource>(HttpStatus.BAD_REQUEST);
		}
	}

}
