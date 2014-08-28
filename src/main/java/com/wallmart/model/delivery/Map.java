package com.wallmart.model.delivery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MAP")
@SequenceGenerator(sequenceName = "SEQ_MAP" , name = "SEQ_MAP",allocationSize=1,initialValue=1)
public class Map implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4585833083839610504L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MAP")
	private Long id;
	
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "MAP_ID")
	private List<Route> routes;

	public Map() {
		super();
	}
	
	public Map(String name) {
		this.name = name;
	}

	public Map(long id, String name) {
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
	
	public List<Route> getRoutes() {
		return routes;
	}
	
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public void addRoute(String origin, String destination, int distance) {
		List<Route> routes = new ArrayList<Route>();
		if(this.getRoutes() != null){
			routes = this.getRoutes();
		}
		routes.add(new Route(origin,destination,distance));
		setRoutes(routes);
	}
	
}
