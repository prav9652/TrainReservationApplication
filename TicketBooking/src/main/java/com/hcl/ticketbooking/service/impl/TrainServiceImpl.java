package com.hcl.ticketbooking.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ticketbooking.entity.Train;
import com.hcl.ticketbooking.repository.TrainRepository;
import com.hcl.ticketbooking.service.TrainService;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

	@Autowired
	private TrainRepository trainRepository;

	@Override
	public void addTrain(Train train) {
		trainRepository.save(train);
	}

	@Override
	public List<Train> getAllAvailableTrains(String source, String destination, String starDate) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(starDate, dateTimeFormatter);

		List<Train> trains = trainRepository.findBySourceAndDestinationAndStartDate(source, destination, localDate);
		if (trains != null) {
			return trains;
		}
		return null;
	}
}