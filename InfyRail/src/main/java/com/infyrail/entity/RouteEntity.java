package com.infyrail.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class RouteEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String source;
	private String destination;
	@OneToMany(cascade = CascadeType.ALL)
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
	
	

}
