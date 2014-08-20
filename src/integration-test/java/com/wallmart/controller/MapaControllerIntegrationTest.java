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
import com.wallmart.model.entrega.Mapa;
import com.wallmart.rest.json.MapaJSON;
import com.wallmart.rest.json.RotaJSON;
import com.wallmart.service.MapaServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MapaControllerIntegrationTest extends BaseControllerTest{

	private final static String MAPA_CALL = "/mapa/";
	
	MapaController mapaController = new MapaController();
	
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private MapaServiceImpl mapaService;

    @Before
    public void setUp() {
    	initIntegration(webApplicationContext);
    }
	
	@Test
	public void deveRealizarAdicaoDeUmMapa() throws Exception{
		MapaJSON mapaJSON = getMapasJSON().get(0);
		getMockMvc().perform(post(MAPA_CALL).content(new Gson().toJson(mapaJSON)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagem", is(Constants.SUCESSO)));
	}
	
	@Test
	public void deveVerificarMapaParaDelecao() throws Exception{
		Mapa mapa = mapaService.buscarMapaPorNome("teste-integration-controller");
		getMockMvc().perform(delete(MAPA_CALL+"{id}",mapa.getId()+""))
        .andExpect(status().isOk())
		.andExpect(jsonPath("$.mensagem", is(Constants.SUCESSO)));
	}
	
	private List<MapaJSON> getMapasJSON() {
		MapaJSON mapaJSON = new MapaJSON(1L,"teste-integration-controller");
		RotaJSON rotaJSON = new RotaJSON("origem","destino",1);
		List<RotaJSON> rotas = new ArrayList<RotaJSON>();
		rotas.add(rotaJSON);
		mapaJSON.setRotas(rotas);
		List<MapaJSON> mapas = new ArrayList<MapaJSON>();
		mapas.add(mapaJSON);
		return mapas;
	}
	
}