package com.infyrail.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infyrail.dto.NoSuchRouteException;
import com.infyrail.dto.RouteDTO;
import com.infyrail.dto.TrainDTO;
import com.infyrail.service.RouteService;

@RestController
@RequestMapping("routes")
@Validated
public class RouteController {
	@Autowired
	private RouteService routeService;
	
	@Autowired
	private Environment env;
	
	@GetMapping(value = "{routeId}")
	public ResponseEntity<RouteDTO> getRouteDetails(@PathVariable("routeId") @Pattern(regexp = "[0-9]{3}",message = "{route.routeId.invalid}") Integer id) throws NoSuchRouteException{
		RouteDTO routeDTO = routeService.getRouteDetails(id);
		return new ResponseEntity<RouteDTO>(routeDTO,HttpStatus.OK);		
	}
	
	@PostMapping
	public ResponseEntity<String> createRoute(@Valid @RequestBody RouteDTO routeDTO) throws Exception{
		Integer id = routeService.createRoute(routeDTO);
		String message = env.getProperty("route.create.success")+id.toString();
		return new ResponseEntity<String>(message,HttpStatus.CREATED);		
	}
	
	@GetMapping(value = "trains")
	public ResponseEntity<List<TrainDTO>> getTrains(@RequestParam String source, @RequestParam String destination) throws NoSuchRouteException{
		List<TrainDTO> trains = routeService.getTrains(source, destination);
		return new ResponseEntity<List<TrainDTO>>(trains,HttpStatus.OK);
	}

}
