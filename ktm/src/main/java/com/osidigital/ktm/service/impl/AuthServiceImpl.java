package com.osidigital.ktm.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.osidigital.ktm.exception.APIException;
import com.osidigital.ktm.model.User;
import com.osidigital.ktm.payload.LoginRequest;
import com.osidigital.ktm.payload.SignUpRequest;
import com.osidigital.ktm.repository.RoleRepository;
import com.osidigital.ktm.repository.UserRepository;
import com.osidigital.ktm.security.JwtTokenProvider;
import com.osidigital.ktm.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService{

	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private RoleRepository roleRepository;
	private JwtTokenProvider jwtTokenProvider;
	
	
	public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
			PasswordEncoder passwordEncoder, RoleRepository roleRepository, JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
		this.jwtTokenProvider = jwtTokenProvider;
	}


	@Override
	public String login(LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateToken(authentication);
		
		return token;
	}


	@Override
	public String createUser(SignUpRequest signUpRequest) {
		if(userRepository.existsByUsername(signUpRequest.getUsername()) || userRepository.existsByEmail(signUpRequest.getEmail()))
			throw new APIException(HttpStatus.BAD_REQUEST,"User already exists");
	User user = new User();
	user.setName(signUpRequest.getName());
	user.setEmail(signUpRequest.getEmail());
	user.setUsername(signUpRequest.getUsername());
	user.setPassword(signUpRequest.getPassword());
	user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));		
	user.setRole(roleRepository.findByName(signUpRequest.getRole())
			.orElseThrow(() -> new RuntimeException("Role not found")));
	userRepository.save(user);
	
	return "User successfully created";	
	}

}
