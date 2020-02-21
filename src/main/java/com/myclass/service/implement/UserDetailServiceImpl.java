package com.myclass.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myclass.dto.UserDetailDTO;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userDAO;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<User> list = userDAO.findByEmail(email);
		if (!list.isEmpty()) {
			User user = list.get(0);
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			String rolename = user.getRole().getName();
			authorities.add(new SimpleGrantedAuthority(rolename));
			return new UserDetailDTO(user.getEmail(), user.getPassword(), authorities);
		} else {
			throw new UsernameNotFoundException("Email không tồn tại!");
		}
	}
}
