package com.wallmart.model.delivery;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@javax.persistence.Entity
@Table(name = "ROUTE")
@SequenceGenerator(sequenceName = "SEQ_ROUTE" , name = "SEQ_ROUTE",allocationSize=1,initialValue=1)
public class Route implements Serializable,Comparable<Route> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3568333045541297439L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ROUTE")
	private Long id;

	private String origin;
	
	private String destination;

	//FIXME BIGDECIMAL
	private Integer distance;
	
	public Route() {
		super();
	}

	public Route(String origin, String destination, Integer distance) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(Route route) {
		if(this.getDistance().intValue() > route.getDistance().intValue()){
			return 1;
		}else if(this.getDistance().intValue() < route.getDistance().intValue()){
			return -1;
		}
		return 0;
	}
}
