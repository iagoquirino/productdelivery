package com.wallmart.controller.validators;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import com.wallmart.constants.Constants;
import com.wallmart.exception.APIException;
import com.wallmart.model.delivery.Map;
import com.wallmart.rest.json.MapJSON;
import com.wallmart.rest.json.RouteJSON;
import com.wallmart.service.MapServiceImpl;

public class MapControllerValidatorTest {

	private MapControllerValidator mapControllerValidator = new MapControllerValidator();

	@Mock
	private MapServiceImpl mapService;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mapControllerValidator.setMapService(mapService);
	}
	
	@Test
	public void validateNull(){
		try{
			mapControllerValidator.validate(null);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.ITEN_INVALID, e.getMessageJSON().getMessage());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}
	
	@Test
	public void validateNullName(){
		try{
			mapControllerValidator.validate(new MapJSON());
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.INVALID_MAP_NAME, e.getMessageJSON().getMessage());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}
	
	@Test
	public void validateAlreadyRegistredMap(){
		try{
			Mockito.when(mapService.find(Mockito.anyString())).thenReturn(new Map());
			mapControllerValidator.validate(new MapJSON("test"));
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.ALREADY_REGISTERED_MAP, e.getMessageJSON().getMessage());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}

	@Test
	public void validateEmptyRoutes(){
		try{
			mapControllerValidator.validate(new MapJSON("map"));
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.INVALID_ROUTES_MAP, e.getMessageJSON().getMessage());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}
	
	@Test
	public void validateInvalidRoutes(){
		try{
			MapJSON map = new MapJSON("map");
			RouteJSON nullRoute = null;
			map.setRoutes(Arrays.asList(nullRoute));
			mapControllerValidator.validate(map);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.INVALID_ROUTE, e.getMessageJSON().getMessage());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}
	
	@Test
	public void vaidMapWithInvalidOrigin(){
		try{
			MapJSON map = new MapJSON("map");
			RouteJSON invalidRoute = new RouteJSON();
			map.setRoutes(Arrays.asList(invalidRoute));
			mapControllerValidator.validate(map);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.INVALID_ORIGIN_ROUTE, e.getMessageJSON().getMessage());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}
	
	@Test
	public void validMapWithInvalidDestination(){
		try{
			MapJSON map = new MapJSON("map");
			RouteJSON route = new RouteJSON("origin","",0);
			map.setRoutes(Arrays.asList(route));
			mapControllerValidator.validate(map);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.INVALID_DESTINATION_ROUTE, e.getMessageJSON().getMessage());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}

	@Test
	public void validateMapsWithInvalidDistance(){
		try{
			MapJSON map = new MapJSON("map");
			RouteJSON route = new RouteJSON("origin","destination",0);
			map.setRoutes(Arrays.asList(route));
			mapControllerValidator.validate(map);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.INVALID_DISTANCE_ROUTE, e.getMessageJSON().getMessage());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}
	
	@Test
	public void validateRepeatedRoute(){
		try{
			MapJSON map = new MapJSON("map");
			RouteJSON route1 = new RouteJSON("origin","destination",1);
			RouteJSON route2 = new RouteJSON("origin","destination",2);
			map.setRoutes(Arrays.asList(route1,route2));
			mapControllerValidator.validate(map);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.REPEATED_MAP, e.getMessageJSON().getMessage());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}
	
	@Test
	public void dontValidate(){
		try{
			MapJSON map = new MapJSON("map");
			RouteJSON route = new RouteJSON("origin","destination",1);
			map.setRoutes(Arrays.asList(route));
			mapControllerValidator.validate(map);	
		}catch(APIException e){
			Assert.fail();
		}
	}
	
}
