package com.myclass.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.AuthenticateInfo;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
import com.myclass.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private UserRepository userDAO;
	
	@Override
	public AuthenticateInfo createUserInfo(String email) {
		List<User> list = userDAO.findByEmail(email);
		if (!list.isEmpty()) {
			User user = list.get(0);
			return new AuthenticateInfo(user.getId(), user.getFullname(), user.getEmail(), user.getPassword(),
					user.getRole().getName());
		} else {
			return null;
		}
	}

}
