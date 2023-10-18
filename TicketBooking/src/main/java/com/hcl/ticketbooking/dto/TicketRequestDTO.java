package com.hcl.ticketbooking.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TicketRequestDTO {

	@Pattern(regexp = "^[0-9]*$", message = "must be a number")
	@NotEmpty(message = "TrainNumber is Mandatory")
	private String trainNumber;

	@NotEmpty(message = "List of Passengers cannot be null")
	private List<PassengerDTO> passengers = new ArrayList<PassengerDTO>(6);

	@JsonIgnore
	private String userName;
}