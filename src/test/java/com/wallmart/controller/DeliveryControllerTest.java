package com.wallmart.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;

import com.wallmart.controller.validators.DeliveryControllerValidator;
import com.wallmart.converters.DeliveryJSONConverter;
import com.wallmart.model.delivery.Map;
import com.wallmart.model.vo.DeliveryVO;
import com.wallmart.rest.json.DeliveryJSON;
import com.wallmart.rest.json.PointJSON;
import com.wallmart.rest.json.RouteJSON;
import com.wallmart.service.DeliveryServiceImpl;
import com.wallmart.service.MapServiceImpl;

public class DeliveryControllerTest extends BaseControllerTest {
	
	private String DELIVERY_CALL = "/deliveries/{idMap}/routes";
	
	private String DELIVERY_ROUTE_CALL = "/deliveries/routes";
	
	private DeliveryController controller = new DeliveryController();
	
	@Mock
	private DeliveryControllerValidator deliveryControllerValidator;
	
	@Mock
	private DeliveryServiceImpl deliveryService;
	
	@Mock
	private MapServiceImpl mapService;
	
	@Mock
	private DeliveryJSONConverter deliveryJSONConverter;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		this.init(controller);
		controller.setDeliveryValidator(deliveryControllerValidator);
		controller.setDeliveryJSONConverter(deliveryJSONConverter);
		controller.setDeliveryService(deliveryService);
		controller.setMapService(mapService);
	}
	
	@Test
	public void peformBestRoutes() throws Exception{
		RouteJSON routeJSON = new RouteJSON("ORIGIN", "DESTINATION", 1 ,2.5);
		Long idMap = 1L;
		Map map = new Map();
		DeliveryJSON deliveryJSON = getDeliveryMockJSON();
		
		Mockito.when(mapService.find(Mockito.eq(idMap))).thenReturn(map);		
		Mockito.when(deliveryJSONConverter.convertToJSON(Mockito.any(DeliveryVO.class))).thenReturn(deliveryJSON);

		getMockMvc().perform(get(DELIVERY_CALL,idMap.toString()).param("origin", routeJSON.getOrigin()).param("destination", routeJSON.getDestination()).param("gasCost", routeJSON.getGasCost().toString()).param("autonomy", routeJSON.getAutonomy().toString()))		
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.cost", is(deliveryJSON.getCost())))
   	    .andExpect(jsonPath("$.points", hasSize(deliveryJSON.getPoints().size())))
        .andExpect(jsonPath("$.points.[0].name", is(deliveryJSON.getPoints().get(0).getName())))
        .andExpect(jsonPath("$.points.[1].name", is(deliveryJSON.getPoints().get(1).getName())));
		
		Mockito.verify(deliveryControllerValidator).validate(Mockito.eq(routeJSON.getOrigin()),Mockito.eq(routeJSON.getDestination()),Mockito.eq(routeJSON.getAutonomy()),Mockito.eq(routeJSON.getGasCost()));
		Mockito.verify(deliveryJSONConverter).convertToJSON(Mockito.any(DeliveryVO.class));
		Mockito.verify(deliveryService).calculateRoutes(Mockito.eq(map), Mockito.eq(routeJSON.getOrigin()), Mockito.eq(routeJSON.getDestination()), Mockito.eq(routeJSON.getAutonomy()), Mockito.eq(routeJSON.getGasCost()));
	}
	
	@Test
	public void performBestRoutesWithNameMap() throws Exception{
		RouteJSON rotaJSON = new RouteJSON("origin", "destination", 1 ,2.5);
		Map map = new Map();
		String namemap = "SP";		
		Mockito.when(mapService.find(Mockito.eq(namemap))).thenReturn(map);
		DeliveryJSON entregaJSON = getDeliveryMockJSON();
		Mockito.when(deliveryJSONConverter.convertToJSON(Mockito.any(DeliveryVO.class))).thenReturn(entregaJSON);
		
		getMockMvc().perform(get(DELIVERY_ROUTE_CALL).param("map", namemap).param("origin", rotaJSON.getOrigin()).param("destination", rotaJSON.getDestination()).param("gasCost", rotaJSON.getGasCost().toString()).param("autonomy", rotaJSON.getAutonomy().toString()))		
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.cost", is(entregaJSON.getCost())))
   	    .andExpect(jsonPath("$.points", hasSize(entregaJSON.getPoints().size())))
        .andExpect(jsonPath("$.points.[0].name", is(entregaJSON.getPoints().get(0).getName())))
        .andExpect(jsonPath("$.points.[1].name", is(entregaJSON.getPoints().get(1).getName())));
		
		Mockito.verify(deliveryControllerValidator).validate(Mockito.eq(rotaJSON.getOrigin()),Mockito.eq(rotaJSON.getDestination()),Mockito.eq(rotaJSON.getAutonomy()),Mockito.eq(rotaJSON.getGasCost()));
		Mockito.verify(deliveryJSONConverter).convertToJSON(Mockito.any(DeliveryVO.class));
		Mockito.verify(deliveryService).calculateRoutes(Mockito.eq(map), Mockito.eq(rotaJSON.getOrigin()), Mockito.eq(rotaJSON.getDestination()), Mockito.eq(rotaJSON.getAutonomy()), Mockito.eq(rotaJSON.getGasCost()));
	}

	private DeliveryJSON getDeliveryMockJSON() {
		DeliveryJSON entregaJSON = new DeliveryJSON();
		entregaJSON.setCost(3.0);
		entregaJSON.setPoints(Arrays.asList(new PointJSON("A"),new PointJSON("B")));
		return entregaJSON;
	}
}
