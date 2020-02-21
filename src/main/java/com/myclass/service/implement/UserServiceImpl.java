package com.myclass.service.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.dto.UserDTO;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userDAO;

	@Override
	public ResponseEntity<Object> findById(String id) {
		if (userDAO.existsById(id)) {
			return new ResponseEntity<Object>(userDAO.findById(id).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<Object> insertUser(User user) {
		try {
			if (user != null) {
				user.setId(UUID.randomUUID().toString().replace("-", ""));
				String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
				user.setPassword(hashed);
				userDAO.save(user);
				return new ResponseEntity<Object>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Thông tin người dùng không đầy đủ!", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Lỗi không xác định!", HttpStatus.EXPECTATION_FAILED);
		}
	}

	@Override
	public ResponseEntity<Object> updateUser(String id, User user) {
		User entity = userDAO.findById(id).get();
		try {
			if (entity != null) {
				user.setId(entity.getId());
				userDAO.save(user);
				return new ResponseEntity<Object>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Người dùng không tồn tại!", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@Override
	public ResponseEntity<Object> deleteUser(String id) {
		User entity = userDAO.findById(id).get();
		if (entity != null) {
			userDAO.delete(entity);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Người dùng không tồn tại!", HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<Object> findAll() {
		List<User> list = userDAO.findAll();
		if (!list.isEmpty()) {
			List<UserDTO> listDto = new ArrayList<UserDTO>();
			for (int i = 0; i < list.size(); i++) {
				String id = list.get(i).getId();
				String fullname = list.get(i).getFullname();
				String email = list.get(i).getEmail();
				String phone = list.get(i).getPhone();
				String roleDescription = list.get(i).getRole().getDescription();
				UserDTO user = new UserDTO(id, fullname, email, phone, roleDescription);
				listDto.add(user);
			}
			return new ResponseEntity<Object>(listDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<Object> uploadAvatar(MultipartFile fileUploaded, String id) {
		if (fileUploaded != null || id != null) {
			File data = new File(
					"E:/upload/image/user/" + id + "." + fileUploaded.getContentType().replace("image/", ""));
			String path = id + "." + fileUploaded.getContentType().replace("image/", "");
			try {
				data.createNewFile();
				FileOutputStream fos = new FileOutputStream(data);
				fos.write(fileUploaded.getBytes());
				userDAO.setAvatar(path, id);
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
	public ResponseEntity<InputStreamResource> displayAvatar(String id) {
		if (id != null) {
			String pathDefault = "777.png";
			String path = userDAO.getAvatar(id);
			File getData = null;
			if (path != null) {
				getData = new File("E:/upload/image/user/" + path);
			} else {
				getData = new File("E:/upload/image/user/" + pathDefault);
			}
			if (getData.exists()) {
				try {
					InputStream oData = new FileInputStream(getData);
					return ResponseEntity.ok().contentLength(getData.length()).contentType(MediaType.IMAGE_JPEG)
							.contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(oData));
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
