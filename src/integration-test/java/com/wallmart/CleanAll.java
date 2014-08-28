package com.wallmart;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wallmart.model.delivery.Map;
import com.wallmart.service.MapServiceImpl;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class CleanAll {

	@Autowired
	private MapServiceImpl mapaServiceImpl;
	
	@Test
	public void cleanAll(){
		List<Map> mapas = mapaServiceImpl.list();
		for (Map mapa : mapas) {
			mapaServiceImpl.delete(mapa);
		}
	}
	
}
