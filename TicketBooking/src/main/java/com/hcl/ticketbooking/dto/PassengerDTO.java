package com.hcl.ticketbooking.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;




public class PassengerDTO {

	/**
	 * 
	 */
	public PassengerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@NotEmpty(message = "Name is mandatory")
	@Pattern(regexp = "[a-zA-Z]", message = "Enter Alphabets only")
	private String name;
	@NotEmpty(message = "Age is mandatory")
	@Pattern(regexp="^[0-9]*$",message="must be a number")
	private String age;
	@NotEmpty(message = "SeatNumber is mandatory")
	private String SeatNumber;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSeatNumber() {
		return SeatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		SeatNumber = seatNumber;
	}
	/**
	 * @param name
	 * @param age
	 * @param seatNumber
	 */
	public PassengerDTO(@Pattern(regexp = "[a-zA-Z]", message = "Enter Alphabets only") String name,
			@Pattern(regexp = "^[0-9]*$", message = "must be a number") String age, String seatNumber) {
		super();
		this.name = name;
		this.age = age;
		SeatNumber = seatNumber;
	}
}