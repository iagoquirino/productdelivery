package com.wallmart.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;

import com.google.gson.Gson;
import com.wallmart.constants.Constants;
import com.wallmart.controller.validators.MapControllerValidator;
import com.wallmart.converters.MapJSONConverter;
import com.wallmart.exception.ProductDeliveryException;
import com.wallmart.model.delivery.Map;
import com.wallmart.rest.json.MapJSON;
import com.wallmart.rest.json.RouteJSON;
import com.wallmart.service.MapServiceImpl;

public class MapControllerTest extends BaseControllerTest{

	private final static String MAP_CALL = "/maps/";
	
	MapController mapController = new MapController();
	@Mock private MapJSONConverter mapJSONConverter;
	@Mock private MapServiceImpl mapService;
	@Mock private MapControllerValidator mapControllerValidator;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		this.init(mapController);
		mapController.setMapJSONConverter(mapJSONConverter);
		mapController.setMapService(mapService);
		mapController.setMapValidator(mapControllerValidator);
	}
	
	@Test
	public void performListMaps() throws Exception{
		Mockito.when(mapJSONConverter.converToListJSON(Mockito.anyList())).thenReturn(getMapMockJSON());

		getMockMvc().perform(get(MAP_CALL))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
	    .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].name", is("test")))
   	    .andExpect(jsonPath("$[0].routes", hasSize(1)))
        .andExpect(jsonPath("$[0].routes.[0].origin", is("origin")))
        .andExpect(jsonPath("$[0].routes.[0].destination", is("destination")))
        .andExpect(jsonPath("$[0].routes.[0].distance", is(1)));

		Mockito.verify(mapService).list();
		Mockito.verify(mapJSONConverter).converToListJSON(Mockito.anyList());
	}

	@Test
	public void peformListMapReturnEmptyValues() throws Exception{
		Mockito.when(mapJSONConverter.converToListJSON(Mockito.anyList())).thenReturn(getMapMockJSON());

		getMockMvc().perform(get(MAP_CALL))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
	    .andReturn();

	    Mockito.verify(mapService).list();
		Mockito.verify(mapJSONConverter).converToListJSON(Mockito.anyList());
	}
	
	@Test
	public void findMap() throws Exception {
		Mockito.when(mapJSONConverter.convertToJSON(Mockito.any(Map.class))).thenReturn(getMapMockJSON().get(0));
		Long id = 1L;
			getMockMvc().perform(get(MAP_CALL+"{id}",id.toString()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("test")))
			.andExpect(jsonPath("$.routes", hasSize(1)))
			.andExpect(jsonPath("$.routes.[0].origin", is("origin")))
			.andExpect(jsonPath("$.routes.[0].destination", is("destination")))
			.andExpect(jsonPath("$.routes.[0].distance", is(1)));

	    Mockito.verify(mapService).find(Mockito.eq(id));
		Mockito.verify(mapJSONConverter).convertToJSON(Mockito.any(Map.class));	
	}
	
	@Test
	public void validWhenException() throws Exception{
		Mockito.doThrow(new ProductDeliveryException(Constants.ITEN_NOT_FOUND)).when(mapService).find(Mockito.anyLong());
		Long id = 1L;
		getMockMvc().perform(get(MAP_CALL+"{id}",id.toString()))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is(Constants.ITEN_NOT_FOUND)));
	    Mockito.verify(mapService).find(Mockito.eq(id));
	}
	
	@Test
	public void peformDeleteMap() throws Exception{
		Long id = 1L;
		getMockMvc().perform(delete(MAP_CALL+"{id}",id.toString()))
        .andExpect(status().isOk())
		.andExpect(jsonPath("$.message", is(Constants.SUCESS)));
		Mockito.verify(mapService).find(Mockito.anyLong());
		Mockito.verify(mapService).delete(Mockito.any(Map.class));
	}
	
	@Test
	public void peformAddMap() throws Exception{
		Long id = 1L;
		Mockito.when(mapService.save(Mockito.any(Map.class))).thenReturn(id);
		MapJSON mapaJSON = getMapMockJSON().get(0);
		getMockMvc().perform(post(MAP_CALL).content(new Gson().toJson(mapaJSON)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message", is(Constants.SUCESS)))
		.andExpect(jsonPath("$.code", is(id.intValue())));
		Mockito.verify(mapControllerValidator).validate(Mockito.any(MapJSON.class));
		Mockito.verify(mapJSONConverter).convertToModel(Mockito.any(MapJSON.class));
	}
	
	private List<MapJSON> getMapMockJSON() {
		MapJSON mapJSON = new MapJSON(1L,"test");
		RouteJSON routeJSON = new RouteJSON("origin","destination",1);
		List<RouteJSON> routes = new ArrayList<RouteJSON>();
		routes.add(routeJSON);
		mapJSON.setRoutes(routes);
		List<MapJSON> maps = new ArrayList<MapJSON>();
		maps.add(mapJSON);
		return maps;
	}
}
