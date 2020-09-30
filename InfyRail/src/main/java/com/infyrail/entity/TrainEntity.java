package com.infyrail.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.infyrail.dto.TrainDTO;

@Entity
@Table(name = "trains")
public class TrainEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String trainName;
	private String arrivalTime;
	private String departureTime;
	private Double fare;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public TrainEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrainEntity(String trainName, String arrivalTime, String departureTime, Double fare) {
		this();
		this.trainName = trainName;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.fare = fare;
	}
	
	public TrainDTO convertToDTO() {
		return new TrainDTO(this.id,this.trainName,this.arrivalTime,this.departureTime,this.fare);
	}
	
	

}
