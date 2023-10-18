package com.hcl.ticketbooking.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.ticketbooking.entity.Train;

/**
 * @author yash.ghawghawe
 *
 */
@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {

	/**
	 * @param source
	 * @param destination
	 * @param startDate
	 * @return List<Train>
	 */
	List<Train> findBySourceAndDestinationAndStartDate(String source, String destination, LocalDate startDate);
	

	Optional<Train> findByTrainNumber(String trainNumber);
	/**
	 * @param availableSeats
	 * @param trainNumber
	 */
	
	@Modifying
	@Query("update Train t set t.availableSeats=:availableSeats where t.trainNumber =:trainNumber")
	void updateTrain(@Param("availableSeats") int availableSeats,@Param("trainNumber")String trainNumber);	
}