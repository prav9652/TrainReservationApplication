package com.hcl.ticketbooking.service;

import java.util.List;

import com.hcl.ticketbooking.dto.TicketResponseDTO;
import com.hcl.ticketbooking.entity.User;

public interface UserService {

	/**
	 * @param user
	 * @return User
	 */
	User saveUser(User user);

	/**
	 * @param userid
	 * @param user
	 * @return
	 */
	User updateUser(int userid, User user);

	/**
	 * @param userid
	 * @return User
	 */
	User getUser(int userid);

	/**
	 * @param username
	 * @return User
	 */
	User findByUserName(String username);

	/**
	 * @return List<User>
	 */
	List<User> getAllUsers();

	/**
	 * @param userId
	 * @return User
	 */
	User getUserById(int userId);

	/**
	 * @param userid
	 * @return User
	 */
	User deleteUserById(int userid);

	/**
	 * @param userName
	 * @param password
	 * @return User
	 */
	User findByUsernameAndPassword(String userName, String password);

}
