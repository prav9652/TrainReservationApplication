package com.hcl.ticketbooking.entity;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Login {

	@NotEmpty(message = "enter username")
	private String username;

	@NotEmpty(message = "enter password")
	private String password;

}