package com.wallmart.converters;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.wallmart.model.delivery.Map;
import com.wallmart.model.delivery.Route;
import com.wallmart.rest.json.MapJSON;
import com.wallmart.rest.json.RouteJSON;

public class MapJSONConverterTest {

	MapJSONConverter mapJSONConverter = new MapJSONConverter();
	
	@Test
	public void convertToJSON(){
		List<Map> maps = getMockMaps();
		List<MapJSON> listJSON = mapJSONConverter.converToListJSON(maps);
		Assert.assertEquals(listJSON.size(), maps.size());
		assertObject(maps.get(0), listJSON.get(0));
	}
	

	@Test
	public void convertToModel(){
		MapJSON mapJSON = getMapaJSON();
		Map map = mapJSONConverter.convertToModel(mapJSON);
		assertObject(map, mapJSON);
	}

	private MapJSON getMapaJSON() {
		MapJSON mapJSON = new MapJSON(2L,"map");
		RouteJSON route = new RouteJSON("test","test2",1);
		mapJSON.setRoutes(Arrays.asList(route));
		return mapJSON;
	}

	private List<Map> getMockMaps() {
		Map map = new Map(2L,"map");
		Route route = new Route("test2","test3",3);
		map.setRoutes(Arrays.asList(route));		
		return Arrays.asList(map);
	}
	
	private void assertObject(Map map, MapJSON mapJSON) {
		Assert.assertEquals(mapJSON.getId(), map.getId());
		Assert.assertEquals(mapJSON.getName(), map.getName());
		Assert.assertEquals(mapJSON.getRoutes().size(), map.getRoutes().size());
		for (int i = 0; i < map.getRoutes().size(); i++) {
			assertRoutes(mapJSON.getRoutes().get(i),map.getRoutes().get(i));	
		}
	}

	private void assertRoutes(RouteJSON routeJSON, Route route) {
		Assert.assertEquals(routeJSON.getOrigin(), route.getOrigin());
		Assert.assertEquals(routeJSON.getDestination(), route.getDestination());
		Assert.assertEquals(routeJSON.getDistance().intValue(), route.getDistance().intValue());
	}
}
