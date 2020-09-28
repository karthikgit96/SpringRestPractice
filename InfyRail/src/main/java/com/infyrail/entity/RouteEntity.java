package com.infyrail.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.infyrail.dto.RouteDTO;
import com.infyrail.dto.TrainDTO;

@Entity
@Table(name = "routes")
public class RouteEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String source;
	private String destination;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "route_id")
	private List<TrainEntity> trainList;
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
	public List<TrainEntity> getTrainList() {
		return trainList;
	}
	public RouteEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setTrainList(List<TrainEntity> trainList) {
		this.trainList = trainList;
	}
	public RouteEntity(String source, String destination, List<TrainEntity> trainList) {
		this();
		this.source = source;
		this.destination = destination;
		this.trainList = trainList;
	}
	
	public RouteDTO convertToRouteDTO() {
		List<TrainDTO> trainList = new LinkedList<TrainDTO>();
		this.trainList.forEach(c->{
			TrainDTO t = new TrainDTO(c.getId(),c.getTrainName(),c.getArrivalTime(),c.getDepartureTime(),c.getFare());
			trainList.add(t);
		});		
		return new RouteDTO(this.id,this.destination,this.source,trainList);
		
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
		RouteEntity other = (RouteEntity) obj;
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
