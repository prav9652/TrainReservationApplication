package com.hcl.ticketbooking.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ticketbooking.JwtTokenUtil;
import com.hcl.ticketbooking.dto.TicketRequestDTO;
import com.hcl.ticketbooking.dto.TicketResponseDTO;
import com.hcl.ticketbooking.entity.Ticket;
import com.hcl.ticketbooking.response.MessageResponse;
import com.hcl.ticketbooking.service.TicketService;

@RestController

@RequestMapping("/tickets")

public class TicketController {

	private final Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	private TicketService service;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping

	public ResponseEntity<?> bookTickets(HttpServletRequest request, @RequestBody @Valid TicketRequestDTO dto) {
		String token = request.getHeader("Authorization");
		String userName = jwtTokenUtil.getUsernameFromToken(token);
		dto.setUserName(userName);
		Ticket ticket = service.bookTickets(dto);
		if (!ObjectUtils.isEmpty(ticket)) {
			logger.info("Tickets Booked Successfully with ticket number : " + ticket.getTicketNumber());
			return ResponseEntity.ok(new MessageResponse(
					"Tickets Booked Successfully with ticket number : " + ticket.getTicketNumber()));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new MessageResponse("Please Enter The Valid TrainID and UserID"));
	}

	@GetMapping("/{ticketNumber}")
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTicketDetails(@PathVariable String ticketNumber) {
		if (ticketNumber.startsWith("PNR") && ticketNumber.length() == 14) {
			Ticket ticket = service.getTicketDetails(ticketNumber);
			if (!ObjectUtils.isEmpty(ticket)) {
				TicketResponseDTO responseDTO = new TicketResponseDTO();
				BeanUtils.copyProperties(ticket, responseDTO);
				responseDTO.setUserName(ticket.getUser().getUserName());
				return new ResponseEntity<TicketResponseDTO>(responseDTO, HttpStatus.OK);
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("TicketNumber is not Valid"));

	}

	@GetMapping
	public ResponseEntity<?> getUserBookingHistory(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String userName = jwtTokenUtil.getUsernameFromToken(token);
		List<TicketResponseDTO> responseDTO = service.getTicketHistory(userName);
		if (ObjectUtils.isEmpty(responseDTO)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(
					"userName is not Valid OR User(:" + userName + ") didn't booked any ticket yet."));
		}
		return new ResponseEntity<List<TicketResponseDTO>>(responseDTO, HttpStatus.OK);
	}
}
