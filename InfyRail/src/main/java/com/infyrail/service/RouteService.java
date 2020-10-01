package com.infyrail.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infyrail.dto.NoSuchRouteException;
import com.infyrail.dto.RouteAlreadyPresentException;
import com.infyrail.dto.RouteDTO;
import com.infyrail.dto.TrainDTO;
import com.infyrail.entity.RouteEntity;
import com.infyrail.entity.TrainEntity;
import com.infyrail.repository.RouteRepository;
import com.infyrail.repository.TrainRepository;
import com.infyrail.util.InfyRailConstants;

@Service
@Transactional
public class RouteService {
	@Autowired
	private RouteRepository routeRepository;//Instance of route repository to gain access to DAO
	@Autowired
	private TrainRepository trainRepository;
	
	//The below method is used to get route details from the repository
	public RouteDTO getRouteDetails(Integer id) throws NoSuchRouteException{
		Optional<RouteEntity> optional = routeRepository.findById(id);
		RouteEntity routeEntity = optional.orElseThrow(()->new NoSuchRouteException(InfyRailConstants.ROUTE_NOT_FOUND.toString()));
		RouteDTO routeDTO= routeEntity.convertToRouteDTO();
		return routeDTO;		
	}
	
	//Method to create route
	public Integer createRoute(RouteDTO routeDTO) throws RouteAlreadyPresentException{
		Integer retVal = null;
		RouteEntity route = routeDTO.convertToEntity();
		List<RouteEntity> existingList = routeRepository.findAll();
		List<TrainEntity> existingTrains = trainRepository.findAll();
		List<TrainEntity> requiredTrains = new LinkedList<TrainEntity>();
		if(existingList.contains(route)) throw new RouteAlreadyPresentException(InfyRailConstants.ROUTE_ALREADY_PRESENT.toString());
		else {
			route.getTrainList().forEach(c->{
				if(existingTrains.contains(c)) {
					existingTrains.forEach(e->{
						if(e.getTrainName().equalsIgnoreCase(c.getTrainName())) requiredTrains.add(e);
					});
				}
				else requiredTrains.add(c);
			});
			route.setTrainList(requiredTrains);
			routeRepository.saveAndFlush(route);
			retVal = route.getId();
		}
		return retVal;	
	}
	
	//Method to show all routes
	public List<RouteDTO> showRoutes(){
		List<RouteEntity> routeEntities = routeRepository.findAll();
		List<RouteDTO> routes = new LinkedList<RouteDTO>();
		routeEntities.forEach(c->{
			RouteDTO r = c.convertToRouteDTO();
			routes.add(r);
		});
		return routes;
	}
	
	//Method to show Train List based on source and destination
	public List<TrainDTO> getTrains(String source , String destination) throws NoSuchRouteException{
		List<RouteEntity> entities = routeRepository.findAll();
		List<TrainDTO> trains = new LinkedList<TrainDTO>();
		System.out.println(source+" "+destination);
		entities.forEach(c->{
			System.out.println(c.getSource()+" "+c.getDestination());
			if(c.getSource().equalsIgnoreCase(source)&&c.getDestination().equalsIgnoreCase(destination)) {
				System.out.println("found a match!!");
				RouteDTO route = c.convertToRouteDTO();
				trains.addAll(route.getTrains());
			}
		});
		return trains;
	}

}
