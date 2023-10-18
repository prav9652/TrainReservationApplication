package com.hcl.ticketbooking.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ticketbooking.dto.TrainDTO;
import com.hcl.ticketbooking.dto.TrainResponseDTO;
import com.hcl.ticketbooking.entity.Train;
import com.hcl.ticketbooking.exception.TrainNotAvailableException;
import com.hcl.ticketbooking.service.TrainService;

/**
 * @author yash.ghawghawe
 *
 */
@RestController
@RequestMapping("/trains")
public class TrainController {

	private final Logger logger = LoggerFactory.getLogger(TrainController.class);

	@Autowired
	private TrainService trainService;

	/**
	 * @param source
	 * @param destination
	 * @param startDate
	 * @return ResponseEntity<List<TrainResponseDTO>>
	 * @throws ParseException
	 * @throws TrainNotAvailableException
	 */
	@GetMapping
	public ResponseEntity<List<TrainResponseDTO>> getAllAvailableTrains(
			@Valid @Pattern(regexp = "[a-zA-Z]", message = "Enter Alphabets only") @RequestParam String source,
			@Valid @Pattern(regexp = "[a-zA-Z]", message = "Enter Alphabets only") @RequestParam String destination,
			@Valid @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "Enter date in YYYY-MM-DD format") @RequestParam(required = true) String startDate)
			throws TrainNotAvailableException {

		List<Train> trains = trainService.getAllAvailableTrains(source, destination, startDate);
		if (trains.isEmpty()) {
			throw new TrainNotAvailableException("no train is available for the provided data");
		}
		List<TrainResponseDTO> trainDTOs = new ArrayList<TrainResponseDTO>();

		for (Train train : trains) {
			TrainResponseDTO dto = new TrainResponseDTO();
			BeanUtils.copyProperties(train, dto);
			trainDTOs.add(dto);
		}
		logger.info("Available trains fetched for data provided");
		return new ResponseEntity<List<TrainResponseDTO>>(trainDTOs, HttpStatus.OK);
	}

	/**
	 * @param trainDTO
	 */
	@PostMapping
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addTrain(@RequestBody TrainDTO trainDTO) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime localTimeObj = LocalTime.parse(trainDTO.getDepartureTime(), formatter);

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyy");
		LocalDate localDate = LocalDate.parse(trainDTO.getStartDate(), dateTimeFormatter);

		Train train = new Train();
		BeanUtils.copyProperties(trainDTO, train);
		train.setDepartureTime(localTimeObj);
		train.setStartDate(localDate);
		trainService.addTrain(train);
		return new ResponseEntity<>("Train " + trainDTO.getTrainName() + " created successfully", HttpStatus.CREATED);
	}
}