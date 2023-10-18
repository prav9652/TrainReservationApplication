package com.hcl.ticketbooking.dto;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginDTO {
	@NotEmpty(message = "Username is mandatory")
	String username;
	@NotEmpty(message = "Password is mandatory")
	String password;
}