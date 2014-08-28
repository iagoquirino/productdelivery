package com.wallmart.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.wallmart.model.delivery.Map;
import com.wallmart.model.delivery.Route;
import com.wallmart.rest.json.MapJSON;
import com.wallmart.rest.json.RouteJSON;

@Component
public class MapJSONConverter {
	
	String[] ignoresRoute = {"autonomy","gasCost","id"};
	String[] ignores = {"routes"};

	public List<MapJSON> converToListJSON(List<Map> maps) {
		List<MapJSON> mapsJSON = new ArrayList<MapJSON>();
		if(maps != null){
			for (Map map : maps) {
				MapJSON mapJSON = convertToJSON(map);
				if(mapJSON != null){
					mapsJSON.add(mapJSON);
				}
			}
		}
		return mapsJSON;
	}

	public MapJSON convertToJSON(Map map) {
		if(map == null){
			return null;
		}
		MapJSON mapaJSON = new MapJSON();
		BeanUtils.copyProperties(map, mapaJSON, ignores);
		List<RouteJSON> routesJSON = convertToJSON(map.getRoutes());
		mapaJSON.setRoutes(routesJSON);
		return mapaJSON;
	}

	private List<RouteJSON> convertToJSON(List<Route> routes) {
		List<RouteJSON> routesJSON = new ArrayList<RouteJSON>();
		if(routes != null){
			for (Route route : routes) {
				if(route != null){
					RouteJSON routeJSON = new RouteJSON();
					BeanUtils.copyProperties(route, routeJSON,ignoresRoute);
					routesJSON.add(routeJSON);
				}
			}
		}
		return routesJSON;
	}

	public Map convertToModel(MapJSON mapJSON) {
		if(mapJSON == null){
			return null;
		}
		Map map = new Map();
		BeanUtils.copyProperties(mapJSON,map, ignores);
		List<Route> routes = convertToModel(mapJSON.getRoutes());
		map.setRoutes(routes);
		return map;
	}

	private List<Route> convertToModel(List<RouteJSON> routesJSON) {
		List<Route> routes = new ArrayList<Route>();
		if(routesJSON != null){
			for (RouteJSON routeJSON : routesJSON) {
				if(routeJSON != null){
					Route route = new Route();
					BeanUtils.copyProperties(routeJSON,route,ignoresRoute);
					routes.add(route);
				}
			}	
		}
		return routes;
	}
}
