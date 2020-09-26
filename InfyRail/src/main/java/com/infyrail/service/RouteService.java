package com.infyrail.service;

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
	private RouteRepository routeRepository;
	
	public RouteDTO getRouteDetails(Integer id) throws NoSuchRouteException{
		Optional<RouteEntity> optional = routeRepository.findById(id);
		RouteEntity routeEntity = optional.orElseThrow(()->new NoSuchRouteException(InfyRailConstants.ROUTE_NOT_FOUND.toString()));
		RouteDTO routeDTO= routeEntity.convertToRouteDTO();
		return routeDTO;		
	}
	

}
