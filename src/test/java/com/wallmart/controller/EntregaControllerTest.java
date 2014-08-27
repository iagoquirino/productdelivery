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

import com.wallmart.controller.validators.EntregaControllerValidator;
import com.wallmart.converters.EntregaJSONConverter;
import com.wallmart.model.entrega.Mapa;
import com.wallmart.model.vo.EntregaVO;
import com.wallmart.rest.json.EntregaJSON;
import com.wallmart.rest.json.PontoJSON;
import com.wallmart.rest.json.RotaJSON;
import com.wallmart.service.EntregaServiceImpl;
import com.wallmart.service.MapaServiceImpl;

public class EntregaControllerTest extends BaseControllerTest {
	
	private String ENTREGA_CALL = "/entrega/{idMapa}/rota";
	
	private EntregaController controller = new EntregaController();
	
	@Mock
	private EntregaControllerValidator entregaControllerValidator;
	
	@Mock
	private EntregaServiceImpl entregaService;
	
	@Mock
	private MapaServiceImpl mapaService;
	
	@Mock
	private EntregaJSONConverter entregaJSONConverter;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		this.init(controller);
		controller.setEntregaValidator(entregaControllerValidator);
		controller.setEntregaJSONConverter(entregaJSONConverter);
		controller.setEntregaService(entregaService);
		controller.setMapaService(mapaService);
	}
	
	@Test
	public void deveRealizarVerificacaoDeMelhorRotaDeEntrega() throws Exception{
		RotaJSON rotaJSON = new RotaJSON("ORIGEM", "DESTINO", 1 ,2.5);
		Long idMapa = 1L;
		Mapa mapa = new Mapa();
		
		Mockito.when(mapaService.buscar(Mockito.eq(idMapa))).thenReturn(mapa);
		
		EntregaJSON entregaJSON = getEntregaJSON();
		
		Mockito.when(entregaJSONConverter.convertToJSON(Mockito.any(EntregaVO.class))).thenReturn(entregaJSON);
		
		getMockMvc().perform(get(ENTREGA_CALL,idMapa.toString()).param("origem", rotaJSON.getOrigem()).param("destino", rotaJSON.getDestino()).param("valorCombustivel", rotaJSON.getValorCombustivel().toString()).param("autonomia", rotaJSON.getAutonomia().toString()))		
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.custo", is(entregaJSON.getCusto())))
   	    .andExpect(jsonPath("$.pontos", hasSize(entregaJSON.getPontos().size())))
        .andExpect(jsonPath("$.pontos.[0].nome", is(entregaJSON.getPontos().get(0).getNome())))
        .andExpect(jsonPath("$.pontos.[1].nome", is(entregaJSON.getPontos().get(1).getNome())));
		
		Mockito.verify(entregaControllerValidator).validar(Mockito.eq(rotaJSON.getOrigem()),Mockito.eq(rotaJSON.getDestino()),Mockito.eq(rotaJSON.getAutonomia()),Mockito.eq(rotaJSON.getValorCombustivel()));
		Mockito.verify(entregaJSONConverter).convertToJSON(Mockito.any(EntregaVO.class));
		Mockito.verify(entregaService).calcularRota(Mockito.eq(mapa), Mockito.eq(rotaJSON.getOrigem()), Mockito.eq(rotaJSON.getDestino()), Mockito.eq(rotaJSON.getAutonomia()), Mockito.eq(rotaJSON.getValorCombustivel()));
	}

	private EntregaJSON getEntregaJSON() {
		EntregaJSON entregaJSON = new EntregaJSON();
		entregaJSON.setCusto(3.0);
		entregaJSON.setPontos(Arrays.asList(new PontoJSON("A"),new PontoJSON("B")));
		return entregaJSON;
	}
}
