package com.hcl.ticketbooking.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import com.hcl.ticketbooking.dto.TicketRequestDTO;
import com.hcl.ticketbooking.dto.TicketResponseDTO;
import com.hcl.ticketbooking.entity.Ticket;

/**
 * @author yash.ghawghawe
 *
 */
public interface TicketService {

	/**
	 * @param trainId
	 * @param userId 
	 * @param dto
	 * @return Ticket
	 */
	Ticket bookTickets(TicketRequestDTO dto);

	/**
	 * @param ticketNumber
	 * @return Ticket
	 */
	Ticket getTicketDetails(String ticketNumber);

	List<TicketResponseDTO> getTicketHistory(String userName);

	
}
