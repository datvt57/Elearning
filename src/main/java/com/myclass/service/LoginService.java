package com.myclass.service;

import com.myclass.dto.AuthenticateInfo;

public interface LoginService {
	public AuthenticateInfo createUserInfo(String email);
}
