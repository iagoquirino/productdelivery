package com.wallmart.rest.json;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DeliveryJSON {
	
	private List<PointJSON> points;
	//FIXME BIGDECIMAL
	private Double cost;
	
	public List<PointJSON> getPoints() {
		return points;
	}
	public void setPoints(List<PointJSON> points) {
		this.points = points;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
}
