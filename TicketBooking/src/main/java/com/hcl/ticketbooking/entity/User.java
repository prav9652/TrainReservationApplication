package com.hcl.ticketbooking.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class User {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,unique=true)
	private String userName;
	private String password;
	private String email;
	private BigDecimal age;
	private String contactNo;
	//private String userRole; 

	// @OneToMany(targetEntity = Ticket.class, cascade = CascadeType.ALL)

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Ticket> tickets;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, 
	inverseJoinColumns = {@JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles;
	
}