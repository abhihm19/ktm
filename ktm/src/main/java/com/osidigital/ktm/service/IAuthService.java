package com.osidigital.ktm.service;

import com.osidigital.ktm.payload.LoginRequest;
import com.osidigital.ktm.payload.SignUpRequest;

public interface IAuthService {
	
	String login(LoginRequest loginRequest);
	String createUser(SignUpRequest signUpRequest);
}
