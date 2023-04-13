package com.osidigital.ktm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osidigital.ktm.payload.JwtAuthResponse;
import com.osidigital.ktm.payload.LoginRequest;
import com.osidigital.ktm.payload.SignUpRequest;
import com.osidigital.ktm.service.IAuthService;

@RestController
@RequestMapping(value="/api/v1/")
public class AuthController {

	private IAuthService authService;

	public AuthController(IAuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginRequest loginRequest) {
		String token = authService.login(loginRequest);
		
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		return ResponseEntity.ok().body(jwtAuthResponse);
	}
	
	@PostMapping("signup")
    public ResponseEntity<String> createUser(@RequestBody SignUpRequest signUpRequest) {
		String output = authService.createUser(signUpRequest);
    	return ResponseEntity.ok().body(output);	
	}
}
