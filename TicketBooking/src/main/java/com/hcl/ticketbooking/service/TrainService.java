package com.hcl.ticketbooking.service;

import java.util.List;

import com.hcl.ticketbooking.entity.Train;

/**
 * @author yash.ghawghawe
 *
 */
public interface TrainService {

	/**
	 * @param train
	 */
	void addTrain(Train train);

	/**
	 * @param source
	 * @param destination
	 * @param StartDate
	 * @return List<Train>
	 */
	List<Train> getAllAvailableTrains(String source, String destination, String StartDate);

}
