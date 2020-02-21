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

import com.myclass.entity.Category;
import com.myclass.repository.CategoryRepository;
import com.myclass.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final String SRC = "E:/upload/image/category/";

	@Autowired
	private CategoryRepository categoryDAO;

	@Override
	public ResponseEntity<Object> findAll() {
		List<Category> list = categoryDAO.findAll();
		if (!list.isEmpty()) {
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<Object> findById(String id) {
		if (id != null) {
			if (categoryDAO.existsById(id)) {
				return new ResponseEntity<Object>(categoryDAO.findById(id).get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Người dùng không tồn tại!", HttpStatus.NO_CONTENT);
			}
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> insertCategory(Category category) {
		try {
			if (category != null) {
				category.setId(UUID.randomUUID().toString().replace("-", ""));
				categoryDAO.save(category);
				return new ResponseEntity<Object>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Không đầy đủ thông tin!", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Lỗi không xác định!", HttpStatus.EXPECTATION_FAILED);
		}
	}

	@Override
	public ResponseEntity<Object> updateCategory(String id, Category category) {
		Category entity = categoryDAO.findById(id).get();
		try {
			if (entity != null) {
				category.setId(entity.getId());
				categoryDAO.save(category);
				return new ResponseEntity<Object>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Thông tin không đầy đủ!", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Lỗi không xác định!", HttpStatus.EXPECTATION_FAILED);
		}
	}

	@Override
	public ResponseEntity<Object> deleteCategory(String id) {
		Category entity = categoryDAO.findById(id).get();
		if (entity != null) {
			categoryDAO.delete(entity);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Người dùng không tồn tại!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<Object> uploadIcon(MultipartFile fileUploaded, String id) {
		if (fileUploaded != null || id != null) {
			String type = fileUploaded.getContentType().replace("image/", "");
			File data = new File(SRC + id + "." + type);
			String path = id + "." + type;
			try {
				data.createNewFile();
				FileOutputStream fos = new FileOutputStream(data);
				fos.write(fileUploaded.getBytes());
				categoryDAO.setIcon(path, id);
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Object>("Lỗi không xác định!", HttpStatus.EXPECTATION_FAILED);
			}
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Thông tin nhập không đầy đủ!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<InputStreamResource> getIcon(String id) {
		if (id != null) {
			String pathDefault = "777.png";
			String path = categoryDAO.getIcon(id);
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
				return new ResponseEntity<InputStreamResource>(HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			return new ResponseEntity<InputStreamResource>(HttpStatus.BAD_REQUEST);
		}
	}

}
