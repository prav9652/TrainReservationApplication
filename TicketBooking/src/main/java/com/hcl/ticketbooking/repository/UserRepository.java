package com.hcl.ticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ticketbooking.entity.User;

/**
 * @author yash.ghawghawe
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	/**
	 * @param username
	 * @return User
	 */
	//User findByUserName(String username);

	/**
	 * @param userName
	 * @param password
	 * @return User
	 */
	User findByUserNameAndPassword(String userName, String password);

	User findByUserName(String username);
	
	User findFirstByUserNameOrderByUserName(String st);

}
