package com.infyrail.dto;

import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.infyrail.entity.RouteEntity;
import com.infyrail.entity.TrainEntity;

public class RouteDTO {
	private Integer id;
	@NotNull(message = "{route.source.NotNull}")
	@Pattern(regexp = "{route.source.alphabets}")
	private String source;
	private String destination;
	@NotNull(message = "{route.trains.NotNull}")
	private List<TrainDTO> trains;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public List<TrainDTO> getTrains() {
		return trains;
	}
	public void setTrains(List<TrainDTO> trains) {
		this.trains = trains;
	}
	public RouteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RouteDTO(Integer id, String source, String destination, List<TrainDTO> trains) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.trains = trains;
	}
	
	public RouteEntity convertToEntity() {
		List<TrainEntity> trainentities = new LinkedList<TrainEntity>();
		this.trains.forEach(c->{
			TrainEntity t = c.convertToEntity();
			trainentities.add(t);
		});
		return new RouteEntity(this.destination,this.source,trainentities);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RouteDTO other = (RouteDTO) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}
	
	

}
