package com.wallmart.service;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wallmart.model.entrega.Mapa;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MapaServiceIntegrationTest {

	@Autowired
	private MapaServiceImpl mapaServiceImpl;
	
	@Test
	public void deveCriarUmMapa(){
		mapaServiceImpl.salvar(new Mapa("teste-integracao"));
	}
	
	@Test
	public void deveListaOMapa(){
		Mapa mapa = mapaServiceImpl.buscarMapaPorNome("teste-integracao");
		Assert.assertNotNull(mapa);
		Assert.assertEquals(mapa.getNome(), "teste-integracao");
	}
	
	@Test
	public void deveRealizarADelecaoDoMapas(){
		Mapa mapa = mapaServiceImpl.buscarMapaPorNome("teste-integracao");
		mapaServiceImpl.deletar(mapa);
	}
	
	@Test
	public void deveVerificarBanco(){
		Mapa mapa = mapaServiceImpl.buscarMapaPorNome("teste-integracao");
		Assert.assertNull(mapa);
	}
	
}
