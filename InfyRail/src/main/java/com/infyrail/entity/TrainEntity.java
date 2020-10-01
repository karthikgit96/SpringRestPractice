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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((trainName == null) ? 0 : trainName.hashCode());
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
		TrainEntity other = (TrainEntity) obj;
		if (trainName == null) {
			if (other.trainName != null)
				return false;
		} else if (!trainName.equals(other.trainName))
			return false;
		return true;
	}
	
	
	
	

}
