package com.wallmart.rest.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class MapJSON {
	
	private Long id;
	
	private String name;
	
	private List<RouteJSON> routes = new ArrayList<RouteJSON>();
	
	public MapJSON() {
		super();
	}
	
	public MapJSON(String name) {
		this.name = name;
	}
	
	public MapJSON(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<RouteJSON> getRoutes() {
		return routes;
	}
	
	public void setRoutes(List<RouteJSON> routes) {
		this.routes = routes;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
