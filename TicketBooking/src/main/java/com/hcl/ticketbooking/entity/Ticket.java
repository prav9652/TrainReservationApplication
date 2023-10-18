package com.hcl.ticketbooking.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ticketId;
	@Column(unique = true)
	private String ticketNumber;
	
	@Column(columnDefinition = "DATE")
	private LocalDate dateOfJourney;
	@Column(columnDefinition = "TIME")
	private LocalTime startTime;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dateOfBooking;
	private double cost;
	private int duration;
	
//	 @ManyToOne
//	 @JoinColumn(name="user_username", nullable=false)

	 @ManyToOne(targetEntity=User.class)
	 @JoinColumn(name="userName",nullable=false)
	
	 private User user;
	 
	
	
	private String trainNumber;
	private String trainName;
	private String fromStation;
	private String toStation;
	@OneToMany(targetEntity = Passenger.class, cascade = CascadeType.ALL)
	private List<Passenger> passengers = new ArrayList<>();
	

}
