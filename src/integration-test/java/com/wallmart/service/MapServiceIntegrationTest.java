package com.wallmart.service;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wallmart.model.delivery.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MapServiceIntegrationTest {

	private static final String NAME_MAP = "integration-test";
	
	@Autowired
	private MapServiceImpl mapServiceImpl;
	
	@Test
	public void addMap(){
		mapServiceImpl.save(new Map(NAME_MAP));
	}
	
	@Test
	public void checkMap(){
		Map mapa = mapServiceImpl.find(NAME_MAP);
		Assert.assertNotNull(mapa);
		Assert.assertEquals(mapa.getName(), NAME_MAP);
	}
	
	@Test
	public void deleteMap(){
		Map mapa = mapServiceImpl.find(NAME_MAP);
		mapServiceImpl.delete(mapa);
	}
	
	@Test
	public void verifyDataBase(){
		Map mapa = mapServiceImpl.find(NAME_MAP);
		Assert.assertNull(mapa);
	}
	
}
