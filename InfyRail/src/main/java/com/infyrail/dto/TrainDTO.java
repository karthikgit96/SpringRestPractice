package com.infyrail.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.infyrail.entity.TrainEntity;

public class TrainDTO {
	private Integer id;
	@NotNull(message = "{train.name.notnull}")
	private String trainName;
	@NotNull(message = "{train.arrivaltime.notnull}")
	@Pattern(regexp = "[0-1]{2}:[0-9]{2}:[0-9]{2} (AM|PM)",message = "{train.time.invalid}")
	private String arrivalTime;
	@NotNull(message = "{train.departuretime.notnull}")
	@Pattern(regexp = "[0-1]{2}:[0-9]{2}:[0-9]{2} (AM|PM)",message = "{train.time.invalid}")
	private String departureTime;
	@NotNull(message = "{train.fare.notnull}")
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
	
	public TrainDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrainDTO(Integer id, String trainName, String arrivalTime, String departureTime, Double fare) {
		super();
		this.id = id;
		this.trainName = trainName;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.fare = fare;
	}
	
	public TrainEntity convertToEntity() {
		return new TrainEntity(this.trainName,this.arrivalTime,this.departureTime,this.fare);
	}
}
