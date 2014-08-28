package com.wallmart.rest.json;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PointJSON {
	
	private String name;
	
	public PointJSON() {
		super();
	}
	
	public PointJSON(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
