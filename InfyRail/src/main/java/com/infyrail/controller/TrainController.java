package com.infyrail.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infyrail.dto.NoSuchTrainException;
import com.infyrail.dto.TrainAlreadyExistsException;
import com.infyrail.dto.TrainDTO;
import com.infyrail.service.TrainService;

@RestController
@RequestMapping(value = "trains")
public class TrainController {
	@Autowired
	private TrainService trainService;
	@Autowired
	private Environment env;
	
	@PostMapping
	public ResponseEntity<String> createTrain(@Valid @RequestBody TrainDTO trainDTO) throws TrainAlreadyExistsException{
		Integer id = trainService.createTrain(trainDTO);
		String message = env.getProperty("train.create.success")+id.toString();
		return new ResponseEntity<String>(message,HttpStatus.OK);		
	}
	
	@PutMapping(value = "{trainId}")
	public ResponseEntity<String> updateTrainFare(@PathVariable Integer trainId, @RequestBody TrainDTO trainDTO) throws NoSuchTrainException{
		String message = env.getProperty(trainService.updateTrainFare(trainId, trainDTO.getFare()));
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}

}
