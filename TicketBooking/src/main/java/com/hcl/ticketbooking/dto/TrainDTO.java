package com.hcl.ticketbooking.dto;



public class TrainDTO {

	private String trainName;
	private String trainNumber;
	private String source;
	private String destination;

	private int availableSeats;
	private int duration;

	private Double fare;
	private String departureTime;
	private String startDate;
	/**
	 * 
	 */
	public TrainDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param trainName
	 * @param trainNumber
	 * @param source
	 * @param destination
	 * @param availableSeats
	 * @param duration
	 * @param fare
	 * @param departureTime
	 * @param startDate
	 */
	public TrainDTO(String trainName, String trainNumber, String source, String destination, int availableSeats,
			int duration, Double fare, String departureTime, String startDate) {
		super();
		this.trainName = trainName;
		this.trainNumber = trainNumber;
		this.source = source;
		this.destination = destination;
		this.availableSeats = availableSeats;
		this.duration = duration;
		this.fare = fare;
		this.departureTime = departureTime;
		this.startDate = startDate;
	}
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
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	
	
}
