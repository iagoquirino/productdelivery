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
import com.wallmart.controller.validators.MapaControllerValidator;
import com.wallmart.converters.MapaJSONConverter;
import com.wallmart.exception.EntregaMercadoriaException;
import com.wallmart.model.entrega.Mapa;
import com.wallmart.rest.json.MapaJSON;
import com.wallmart.rest.json.RotaJSON;
import com.wallmart.service.MapaServiceImpl;

public class MapaControllerTest extends BaseControllerTest{

	private final static String MAPA_CALL = "/mapas/";
	
	MapaController mapaController = new MapaController();
	@Mock private MapaJSONConverter mapaJSONConverter;
	@Mock private MapaServiceImpl mapaService;
	@Mock private MapaControllerValidator mapaControllerValidator;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		this.init(mapaController);
		mapaController.setMapaJSONConverter(mapaJSONConverter);
		mapaController.setMapaService(mapaService);
		mapaController.setMapaValidator(mapaControllerValidator);
	}
	
	@Test
	public void deveRealizarListagemDeMapasRetornandoValores() throws Exception{
		Mockito.when(mapaJSONConverter.converToListJSON(Mockito.anyList())).thenReturn(getMapasJSON());

		getMockMvc().perform(get(MAPA_CALL))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
	    .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].nome", is("teste")))
   	    .andExpect(jsonPath("$[0].rotas", hasSize(1)))
        .andExpect(jsonPath("$[0].rotas.[0].origem", is("origem")))
        .andExpect(jsonPath("$[0].rotas.[0].destino", is("destino")))
        .andExpect(jsonPath("$[0].rotas.[0].distancia", is(1)));

		Mockito.verify(mapaService).listar();
		Mockito.verify(mapaJSONConverter).converToListJSON(Mockito.anyList());
	}

	@Test
	public void deveRealizarListagemDeMapasRetornandoValoresVazio() throws Exception{
		Mockito.when(mapaJSONConverter.converToListJSON(Mockito.anyList())).thenReturn(getMapasJSON());

		getMockMvc().perform(get(MAPA_CALL))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
	    .andReturn();

	    Mockito.verify(mapaService).listar();
		Mockito.verify(mapaJSONConverter).converToListJSON(Mockito.anyList());
	}
	
	@Test
	public void deveBuscarMapasPorId() throws Exception {
		Mockito.when(mapaJSONConverter.convertToJSON(Mockito.any(Mapa.class))).thenReturn(getMapasJSON().get(0));
		Long id = 1L;
			getMockMvc().perform(get(MAPA_CALL+"{id}",id.toString()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.nome", is("teste")))
			.andExpect(jsonPath("$.rotas", hasSize(1)))
			.andExpect(jsonPath("$.rotas.[0].origem", is("origem")))
			.andExpect(jsonPath("$.rotas.[0].destino", is("destino")))
			.andExpect(jsonPath("$.rotas.[0].distancia", is(1)));

	    Mockito.verify(mapaService).buscar(Mockito.eq(id));
		Mockito.verify(mapaJSONConverter).convertToJSON(Mockito.any(Mapa.class));	
	}
	
	@Test
	public void deveValidarQuandoLancadoUmaExcecao() throws Exception{
		Mockito.doThrow(new EntregaMercadoriaException(Constants.ITEM_NAO_ENCONTRADO)).when(mapaService).buscar(Mockito.anyLong());
		Long id = 1L;
		getMockMvc().perform(get(MAPA_CALL+"{id}",id.toString()))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.mensagem", is(Constants.ITEM_NAO_ENCONTRADO)));
	    Mockito.verify(mapaService).buscar(Mockito.eq(id));
	}
	
	@Test
	public void deveRealizarDelecaoDeUmMapa() throws Exception{
		Long id = 1L;
		getMockMvc().perform(delete(MAPA_CALL+"{id}",id.toString()))
        .andExpect(status().isOk())
		.andExpect(jsonPath("$.mensagem", is(Constants.SUCESSO)));
		Mockito.verify(mapaService).buscar(Mockito.anyLong());
		Mockito.verify(mapaService).deletar(Mockito.any(Mapa.class));
	}
	
	@Test
	public void deveRealizarAdicaoDeUmMapa() throws Exception{
		Long id = 1L;
		Mockito.when(mapaService.salvar(Mockito.any(Mapa.class))).thenReturn(id);
		MapaJSON mapaJSON = getMapasJSON().get(0);
		getMockMvc().perform(post(MAPA_CALL).content(new Gson().toJson(mapaJSON)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.mensagem", is(Constants.SUCESSO)))
		.andExpect(jsonPath("$.codigo", is(id.intValue())));
		Mockito.verify(mapaControllerValidator).validar(Mockito.any(MapaJSON.class));
		Mockito.verify(mapaJSONConverter).convertToModel(Mockito.any(MapaJSON.class));
	}
	
	private List<MapaJSON> getMapasJSON() {
		MapaJSON mapaJSON = new MapaJSON(1L,"teste");
		RotaJSON rotaJSON = new RotaJSON("origem","destino",1);
		List<RotaJSON> rotas = new ArrayList<RotaJSON>();
		rotas.add(rotaJSON);
		mapaJSON.setRotas(rotas);
		List<MapaJSON> mapas = new ArrayList<MapaJSON>();
		mapas.add(mapaJSON);
		return mapas;
	}
}
