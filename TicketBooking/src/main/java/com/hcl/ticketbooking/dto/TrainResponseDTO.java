package com.hcl.ticketbooking.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TrainResponseDTO {

	@Override
	public String toString() {
		return "TrainResponseDTO [trainName=" + trainName + ", trainNumber=" + trainNumber + ", source=" + source
				+ ", destination=" + destination + ", availableSeats=" + availableSeats + ", duration=" + duration
				+ ", fare=" + fare + ", startDate=" + startDate + ", departureTime=" + departureTime + "]";
	}

	private String trainName;
	private String trainNumber;
	private String source;
	private String destination;

	private int availableSeats;
	private int duration;

	private Double fare;

	private LocalDate startDate;
	private LocalTime departureTime;

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

}
