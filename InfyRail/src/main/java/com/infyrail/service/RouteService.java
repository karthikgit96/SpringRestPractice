package com.infyrail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infyrail.dto.NoSuchRouteException;
import com.infyrail.dto.RouteDTO;
import com.infyrail.entity.RouteEntity;
import com.infyrail.repository.RouteRepository;
import com.infyrail.util.InfyRailConstants;

@Service
@Transactional
public class RouteService {
	@Autowired
	private RouteRepository routeRepository;//Instance of route repository to gain access to DAO
	
	//The below method is used to get route details from the repository
	public RouteDTO getRouteDetails(Integer id) throws NoSuchRouteException{
		Optional<RouteEntity> optional = routeRepository.findById(id);
		RouteEntity routeEntity = optional.orElseThrow(()->new NoSuchRouteException(InfyRailConstants.ROUTE_NOT_FOUND.toString()));
		RouteDTO routeDTO= routeEntity.convertToRouteDTO();
		return routeDTO;		
	}
	
	//Method to create route
	public Integer createRoute(RouteDTO routeDTO) throws Exception{
		Integer retVal = null;
		RouteEntity route = routeDTO.convertToEntity();
		List<RouteEntity> existingList = routeRepository.findAll();
		if(existingList.contains(route)) throw new Exception(InfyRailConstants.ROUTE_ALREADY_PRESENT.toString());
		else {
			routeRepository.saveAndFlush(route);
			retVal = route.getId();
		}
		return retVal;	
	}

}
