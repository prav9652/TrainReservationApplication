package com.hcl.ticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ticketbooking.entity.Ticket;
import com.hcl.ticketbooking.entity.User;

/**
 * @author yash.ghawghawe
 *
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	/**
	 * @param ticketNumber
	 * @return Ticket
	 */
	Ticket findByTicketNumber(String ticketNumber);

	
	/**
	 * @param userId
	 * @return Ticket
	 */
	List<Ticket> findByUser(User user);
	
	

}
