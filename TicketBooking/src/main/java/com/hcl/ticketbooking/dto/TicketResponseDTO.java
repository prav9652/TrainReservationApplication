package com.hcl.ticketbooking.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.hcl.ticketbooking.entity.Passenger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class TicketResponseDTO {

	@Override
	public String toString() {
		return "TicketResponseDTO [ticketNumber=" + ticketNumber + ", userName=" + userName + ", dateOfJourney="
				+ dateOfJourney + ", dateOfBooking=" + dateOfBooking + ", startTime=" + startTime + ", cost=" + cost
				+ ", duration=" + duration + ", trainNumber=" + trainNumber + ", trainName=" + trainName
				+ ", fromStation=" + fromStation + ", toStation=" + toStation + ", passengers=" + passengers + "]";
	}
	private String ticketNumber;
	private String userName;
	private LocalDate dateOfJourney;
	private LocalDateTime dateOfBooking;
	private LocalTime startTime;
	private double cost;
	private int duration;
	private String trainNumber;
	private String trainName;
	private String fromStation;
	private String toStation;
	private List<Passenger> passengers = new ArrayList<>();
	
	/**
	 * 
	 */
	public TicketResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param ticketNumber
	 * @param userName
	 * @param dateOfJourney
	 * @param dateOfBooking
	 * @param startTime
	 * @param cost
	 * @param duration
	 * @param trainNumber
	 * @param trainName
	 * @param fromStation
	 * @param toStation
	 * @param passengers
	 */
	public TicketResponseDTO(String ticketNumber, String userName, LocalDate dateOfJourney, LocalDateTime dateOfBooking,
			LocalTime startTime, double cost, int duration, String trainNumber, String trainName, String fromStation,
			String toStation, List<Passenger> passengers) {
		super();
		this.ticketNumber = ticketNumber;
		this.userName = userName;
		this.dateOfJourney = dateOfJourney;
		this.dateOfBooking = dateOfBooking;
		this.startTime = startTime;
		this.cost = cost;
		this.duration = duration;
		this.trainNumber = trainNumber;
		this.trainName = trainName;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.passengers = passengers;
	}
	
	
}
