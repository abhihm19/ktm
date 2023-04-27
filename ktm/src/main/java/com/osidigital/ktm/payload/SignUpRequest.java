package com.osidigital.ktm.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
	
	@NotNull
	@Size(min = 3, max = 20, message = "name size must be between 3 and 20")
	private String name;
	@NotNull
	@Size(min = 4, max = 20, message = "username size must be between 4 and 20")
	private String username;
	@NotNull
	@Email(message = "Invalid email address")
	private String email;
	@NotNull
	@Size(min = 4, max = 20, message = "password size must be between 4 and 20")
	private String password;
	
	private String role;
	
}
