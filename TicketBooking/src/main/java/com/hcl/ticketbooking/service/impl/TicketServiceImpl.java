package com.hcl.ticketbooking.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.hcl.ticketbooking.JwtTokenUtil;
import com.hcl.ticketbooking.dto.PassengerDTO;
import com.hcl.ticketbooking.dto.TicketRequestDTO;
import com.hcl.ticketbooking.dto.TicketResponseDTO;
import com.hcl.ticketbooking.entity.Passenger;
import com.hcl.ticketbooking.entity.Ticket;
import com.hcl.ticketbooking.entity.Train;
import com.hcl.ticketbooking.entity.User;
import com.hcl.ticketbooking.repository.TicketRepository;
import com.hcl.ticketbooking.repository.TrainRepository;
import com.hcl.ticketbooking.service.TicketService;
import com.hcl.ticketbooking.service.UserService;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	private final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	private static long tempPNR;
	@Autowired
	private TrainRepository trainRepo;

	@Autowired
	private TicketRepository ticketRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * @see com.hcl.ticketbooking.service.TicketService#bookTickets(long,
	 *      com.hcl.ticketbooking.dto.TicketRequestDTO)
	 */
	@Override
	public Ticket bookTickets(TicketRequestDTO dto) {

		User user = userService.findByUserName(dto.getUserName());
		if (ObjectUtils.isEmpty(user)) {
			return null;
		}
		Optional<Train> train = trainRepo.findByTrainNumber(dto.getTrainNumber());
		if (!ObjectUtils.isEmpty(train)) {
			logger.info("train exists with trainNumber : " + dto.getTrainNumber());
			Train savedTrain = train.get();
			System.out.println(savedTrain);
			if (dto.getPassengers().size() <= 6 && dto.getPassengers().size() < savedTrain.getAvailableSeats()) {
				logger.info("Number of seats are " + dto.getPassengers().size());
				Ticket ticket = new Ticket();
				List<PassengerDTO> passengerDTOs = dto.getPassengers();
				List<Passenger> passengers = new ArrayList<>();
				for (PassengerDTO passengerDTO : passengerDTOs) {
					Passenger passenger = new Passenger();
					BeanUtils.copyProperties(passengerDTO, passenger);
					passengers.add(passenger);
				}
				ticket.setUser(user);
				ticket.setTrainNumber(savedTrain.getTrainNumber());
				ticket.setTrainName(savedTrain.getTrainName());
				ticket.setDateOfJourney(savedTrain.getStartDate());
				ticket.setDateOfBooking(LocalDateTime.now());
				ticket.setStartTime(savedTrain.getDepartureTime());
				ticket.setCost(savedTrain.getFare() * dto.getPassengers().size());
				ticket.setDuration(savedTrain.getDuration());
				ticket.setFromStation(savedTrain.getSource());
				ticket.setToStation(savedTrain.getDestination());

				ticket.setTicketNumber(generatePNR());
				ticket.setPassengers(passengers);
				ticketRepo.save(ticket);
				int seats = savedTrain.getAvailableSeats() - dto.getPassengers().size();
				trainRepo.updateTrain(seats, dto.getTrainNumber());
				return ticket;
			}
			logger.error("No of passesngers can`t be greater than available seats");
		}
		return null;
	}

	/**
	 * @param n
	 * @return String
	 */
	static String generatePNR() {

		String numericString = "0123456789";
		StringBuilder sb = new StringBuilder(10);

		for (int i = 0; i < 10; i++) {

			int index = (int) (numericString.length() * Math.random());
			sb.append(numericString.charAt(index));
		}
		sb.insert(new Random().nextInt(sb.length()), tempPNR);
		sb.insert(0, "PNR");
		return sb.toString();
	}

	/**
	 * @see com.hcl.ticketbooking.service.TicketService#getTicketDetails(java.lang.String)
	 */
	@Override
	public Ticket getTicketDetails(String ticketNumber) {
		Ticket ticket = ticketRepo.findByTicketNumber(ticketNumber);
		if (!ObjectUtils.isEmpty(ticket)) {
			return ticket;
		}
		return null;
	}

	@Override
	public List<TicketResponseDTO> getTicketHistory(String userName) {
		List<Ticket> tickets = ticketRepo.findByUser(userService.findByUserName(userName));
		System.out.println();
		if (tickets.isEmpty()) {
			return null;
		}
		List<TicketResponseDTO> ticketResponseDTOs = new ArrayList<>();
		TicketResponseDTO responseDTO;
		for (Ticket ticket : tickets) {
			responseDTO = new TicketResponseDTO();
			BeanUtils.copyProperties(ticket, responseDTO);
			responseDTO.setUserName(ticket.getUser().getUserName());
			ticketResponseDTOs.add(responseDTO);
		}
		return ticketResponseDTOs;
	}

}