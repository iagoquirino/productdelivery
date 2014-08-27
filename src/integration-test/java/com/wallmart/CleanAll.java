package com.wallmart;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wallmart.model.entrega.Mapa;
import com.wallmart.service.MapaServiceImpl;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class CleanAll {

	@Autowired
	private MapaServiceImpl mapaServiceImpl;
	
	@Test
	public void cleanAll(){
		List<Mapa> mapas = mapaServiceImpl.listarTodos();
		for (Mapa mapa : mapas) {
			mapaServiceImpl.deletar(mapa);
		}
	}
	
}
