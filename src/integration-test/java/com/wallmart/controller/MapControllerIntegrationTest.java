package com.wallmart.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.wallmart.constants.Constants;
import com.wallmart.model.delivery.Map;
import com.wallmart.rest.json.MapJSON;
import com.wallmart.rest.json.RouteJSON;
import com.wallmart.service.MapServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MapControllerIntegrationTest extends BaseControllerTest{

	private final static String MAPS_CALL = "/maps/";
	
	MapController mapController = new MapController();
	
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private MapServiceImpl mapService;

    @Before
    public void setUp() {
    	initIntegration(webApplicationContext);
    }
	
	@Test
	public void addMap() throws Exception{
		MapJSON mapJSON = getMapsJSON().get(0);
		getMockMvc().perform(post(MAPS_CALL).content(new Gson().toJson(mapJSON)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message", is(Constants.SUCESS)));
	}
	
	@Test
	public void deleteMap() throws Exception{
		Map map = mapService.find("integration-test-controller");
		getMockMvc().perform(delete(MAPS_CALL+"{id}",map.getId()+""))
        .andExpect(status().isOk())
		.andExpect(jsonPath("$.message", is(Constants.SUCESS)));
	}
	
	private List<MapJSON> getMapsJSON() {
		MapJSON mapJSON = new MapJSON(1L,"integration-test-controller");
		RouteJSON routeJSON = new RouteJSON("origin","destination",1);
		List<RouteJSON> routes = new ArrayList<RouteJSON>();
		routes.add(routeJSON);
		mapJSON.setRoutes(routes);
		List<MapJSON> maps = new ArrayList<MapJSON>();
		maps.add(mapJSON);
		return maps;
	}
	
}