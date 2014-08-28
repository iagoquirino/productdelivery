package com.wallmart.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.wallmart.constants.Constants;
import com.wallmart.exception.ProductDeliveryException;
import com.wallmart.model.delivery.Map;
import com.wallmart.model.vo.DeliveryVO;

public class DeliveryServiceImplTest {

	private DeliveryServiceImpl deliveryServiceImpl = new DeliveryServiceImpl();
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void returnRoutesBetween_A_B_onMap() throws ProductDeliveryException{
		DeliveryVO delivery = deliveryServiceImpl.calculateRoutes(getMockMap(),"A","B",10,2.5);
		Assert.assertNotNull(delivery);
		Assert.assertEquals(delivery.getPoints().size(), 2);
		Assert.assertEquals(delivery.getPoints().get(0).getName(), "A");
		Assert.assertEquals(delivery.getPoints().get(1).getName(), "B");
		Assert.assertEquals(Double.toString(delivery.getCost()), Double.toString(2.5));
	}
	
	@Test
	public void returnRoutesBetween_A_D_onMap() throws ProductDeliveryException{
		DeliveryVO delivery = deliveryServiceImpl.calculateRoutes(getMockMap(),"A","D",10,2.5);
		Assert.assertNotNull(delivery);
		Assert.assertEquals(delivery.getPoints().size(), 3);
		Assert.assertEquals(delivery.getPoints().get(0).getName(), "A");
		Assert.assertEquals(delivery.getPoints().get(1).getName(), "B");
		Assert.assertEquals(delivery.getPoints().get(2).getName(), "D");
		Assert.assertEquals(Double.toString(delivery.getCost()), Double.toString(6.25));
	}
	
	@Test
	public void returnRoutesBetween_A_E_onMap() throws ProductDeliveryException{
		DeliveryVO delivery = deliveryServiceImpl.calculateRoutes(getMockMap(),"A","E",10,2.5);
		Assert.assertNotNull(delivery);
		Assert.assertEquals(delivery.getPoints().size(), 4);
		Assert.assertEquals(delivery.getPoints().get(0).getName(), "A");
		Assert.assertEquals(delivery.getPoints().get(1).getName(), "B");
		Assert.assertEquals(delivery.getPoints().get(2).getName(), "D");
		Assert.assertEquals(delivery.getPoints().get(3).getName(), "E");
		Assert.assertEquals(Double.toString(delivery.getCost()), Double.toString(13.75));
	}
	
	@Test
	public void validateInvalidDestination(){
		try{
			deliveryServiceImpl.calculateRoutes(getMockMap(),"A","F",10,2.5);
			Assert.fail();	
		}catch(ProductDeliveryException e){
			Assert.assertEquals(Constants.ERROR_ROUTE_NOT_FOUND, e.getMessage());
		}
	}
	
	@Test
	public void validateInvalidOriginRoute(){
		try{
			deliveryServiceImpl.calculateRoutes(getMockMap(),"F","A",10,2.5);
			Assert.fail();	
		}catch(ProductDeliveryException e){
			Assert.assertEquals(Constants.ERROR_ROUTE_NOT_FOUND, e.getMessage());
		}
	}
	
	@Test
	public void returnRoutesBetween_B_E_onMap() throws ProductDeliveryException{
		DeliveryVO delivery = deliveryServiceImpl.calculateRoutes(getMockMap(),"B","E",10,2.5);
		Assert.assertNotNull(delivery);
		Assert.assertEquals(delivery.getPoints().size(), 3);
		Assert.assertEquals(delivery.getPoints().get(0).getName(), "B");
		Assert.assertEquals(delivery.getPoints().get(1).getName(), "D");
		Assert.assertEquals(delivery.getPoints().get(2).getName(), "E");
		Assert.assertEquals(Double.toString(delivery.getCost()), Double.toString(11.25));
	}
	
	@Test
	public void returnRoutesBetween_A_B_OnMap2() throws ProductDeliveryException{
		DeliveryVO delivery = deliveryServiceImpl.calculateRoutes(getMockMap2(),"A","B",10,2.5);
		Assert.assertNotNull(delivery);
		Assert.assertEquals(delivery.getPoints().size(), 2);
		Assert.assertEquals(delivery.getPoints().get(0).getName(), "A");
		Assert.assertEquals(delivery.getPoints().get(1).getName(), "B");
		Assert.assertEquals(Double.toString(delivery.getCost()), Double.toString(2.5));
	}
	
	@Test
	public void returnRoutesBetween_A_D_OnMap2() throws ProductDeliveryException{
		DeliveryVO delivery = deliveryServiceImpl.calculateRoutes(getMockMap2(),"A","D",10,2.5);
		Assert.assertNotNull(delivery);
		Assert.assertEquals(delivery.getPoints().size(), 3);
		Assert.assertEquals(delivery.getPoints().get(0).getName(), "A");
		Assert.assertEquals(delivery.getPoints().get(1).getName(), "B");
		Assert.assertEquals(delivery.getPoints().get(2).getName(), "D");
		Assert.assertEquals(Double.toString(delivery.getCost()), Double.toString(6.25));
	}
	
	@Test
	public void returnRoutesBetween_A_E_OnMap2() throws ProductDeliveryException{
		DeliveryVO delivery = deliveryServiceImpl.calculateRoutes(getMockMap2(),"A","E",10,2.5);
		Assert.assertNotNull(delivery);
		Assert.assertEquals(delivery.getPoints().size(), 4);
		Assert.assertEquals(delivery.getPoints().get(0).getName(), "A");
		Assert.assertEquals(delivery.getPoints().get(1).getName(), "B");
		Assert.assertEquals(delivery.getPoints().get(2).getName(), "D");
		Assert.assertEquals(delivery.getPoints().get(3).getName(), "E");
		Assert.assertEquals(Double.toString(delivery.getCost()), Double.toString(13.75));
	}
	
	@Test
	public void returnRoutesBetween_B_E_OnMap2() throws ProductDeliveryException{
		DeliveryVO delivery = deliveryServiceImpl.calculateRoutes(getMockMap2(),"B","E",10,2.5);
		Assert.assertNotNull(delivery);
		Assert.assertEquals(delivery.getPoints().size(), 3);
		Assert.assertEquals(delivery.getPoints().get(0).getName(), "B");
		Assert.assertEquals(delivery.getPoints().get(1).getName(), "D");
		Assert.assertEquals(delivery.getPoints().get(2).getName(), "E");
		Assert.assertEquals(Double.toString(delivery.getCost()), Double.toString(11.25));
	}
	
	@Test
	public void returnRoutesBetween_A_E_OnMap3() throws ProductDeliveryException{
		DeliveryVO delivery = deliveryServiceImpl.calculateRoutes(getMockMap3(),"A","E",10,2.5);
		Assert.assertNotNull(delivery);
		Assert.assertEquals(delivery.getPoints().size(), 3);
		Assert.assertEquals(delivery.getPoints().get(0).getName(), "A");
		Assert.assertEquals(delivery.getPoints().get(1).getName(), "B");
		Assert.assertEquals(delivery.getPoints().get(2).getName(), "E");
		Assert.assertEquals(Double.toString(delivery.getCost()), Double.toString(3.75));
	}
	
	@Test
	public void returnZeroCostWhenOriginAndDestinationAreSame() throws ProductDeliveryException{
		DeliveryVO delivery = deliveryServiceImpl.calculateRoutes(getMockMap4(),"A","A",10,2.5);
		Assert.assertNotNull(delivery);
		Assert.assertEquals(delivery.getPoints().size(), 1);
		Assert.assertEquals(delivery.getPoints().get(0).getName(), "A");
		Assert.assertEquals(Double.toString(delivery.getCost()), Double.toString(0.0));
	}
	
	@Test
	public void validateOriginRouteNotFound() throws ProductDeliveryException{
		try{
			deliveryServiceImpl.calculateRoutes(getMockMap4(),"B","B",10,2.5);
			Assert.fail();	
		}catch(ProductDeliveryException e){
			Assert.assertEquals(Constants.ERROR_ROUTE_NOT_FOUND, e.getMessage());
		}
	}
	
	private Map getMockMap(){
		Map map = new Map();
		map.addRoute("A","B",10);
		map.addRoute("B","D",15);
		map.addRoute("A","C",20);
		map.addRoute("C","D",30);
		map.addRoute("B","E",50);
		map.addRoute("D","E",30);
		return map;
	}
	
	private Map getMockMap2(){
		Map map = new Map();
		map.addRoute("A","B",10);
		map.addRoute("B","D",15);
		map.addRoute("A","C",5);
		map.addRoute("C","D",30);
		map.addRoute("B","E",50);
		map.addRoute("D","E",30);
		return map;
	}
	
	private Map getMockMap3(){
		Map map = new Map();
		map.addRoute("A","B",10);
		map.addRoute("B","D",15);
		map.addRoute("A","C",5);
		map.addRoute("C","D",30);
		map.addRoute("B","E",5);
		map.addRoute("D","E",30);
		return map;
	}
	
	private Map getMockMap4(){
		Map map = new Map();
		map.addRoute("A","A",10);
		return map;
	}
}
