package com.wallmart.rest.json;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RouteJSON {
	
	private String origin;
	
	private String destination;
	
	//FIXME BIGDECIMAL
	private Integer distance;
	
	private Integer autonomy;
	
	private Double gasCost;
	
	public RouteJSON() {
		super();
	}
	
	public RouteJSON(String origin, String destination, Integer distance) {
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
	}
	
	public RouteJSON(String origin, String destination, Integer autonomy, Double gasCost) {
		this.origin = origin;
		this.destination = destination;
		this.autonomy = autonomy;
		this.gasCost = gasCost;
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

	public Integer getAutonomy() {
		return autonomy;
	}

	public void setAutonomia(Integer autonomia) {
		this.autonomy = autonomia;
	}

	public Double getGasCost() {
		return gasCost;
	}

	public void setValorCombustivel(Double valorCombustivel) {
		this.gasCost = valorCombustivel;
	}
}
