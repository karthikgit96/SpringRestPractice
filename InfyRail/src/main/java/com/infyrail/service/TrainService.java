package com.infyrail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infyrail.dto.NoSuchTrainException;
import com.infyrail.dto.TrainAlreadyExistsException;
import com.infyrail.dto.TrainDTO;
import com.infyrail.entity.TrainEntity;
import com.infyrail.repository.TrainRepository;
import com.infyrail.util.InfyRailConstants;

@Service(value = "trainService")
@Transactional
public class TrainService {
	@Autowired
	private TrainRepository trainRepository;
	
	public Integer createTrain(TrainDTO trainDTO) throws TrainAlreadyExistsException{
		Integer retVal = null;
		TrainEntity trainEntity = trainDTO.convertToEntity();
		List<TrainEntity> existingTrains = trainRepository.findAll();
		if(existingTrains.contains(trainEntity)) throw new TrainAlreadyExistsException(InfyRailConstants.TRAIN_ALREADY_EXISTS.toString());
		else {
			trainRepository.saveAndFlush(trainEntity);
			retVal = trainEntity.getId();
		}
		return retVal;
		
	}
	
	public String updateTrainFare(Integer trainId,Double fare) throws NoSuchTrainException{
		String message = null;
		Optional<TrainEntity> optional = trainRepository.findById(trainId);
		TrainEntity trainEntity = optional.orElseThrow(()->new NoSuchTrainException(InfyRailConstants.TRAIN_NOT_FOUND.toString()));
		trainEntity.setFare(fare);
		trainRepository.saveAndFlush(trainEntity);
		message = InfyRailConstants.TRAIN_FARE_UPDATE_SUCCESS.toString();
		return message;
	}

}
