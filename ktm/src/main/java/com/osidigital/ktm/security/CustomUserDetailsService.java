package com.osidigital.ktm.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.osidigital.ktm.model.User;
import com.osidigital.ktm.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
			.orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));
		
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(authority);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), 
				user.getPassword(),
				authorities);
	}

}
