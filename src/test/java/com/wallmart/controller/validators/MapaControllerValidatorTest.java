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
import com.wallmart.model.entrega.Mapa;
import com.wallmart.rest.json.MapaJSON;
import com.wallmart.rest.json.RotaJSON;
import com.wallmart.service.MapaServiceImpl;

public class MapaControllerValidatorTest {

	private MapaControllerValidator mapaControllerValidator = new MapaControllerValidator();

	@Mock
	private MapaServiceImpl mapaService;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mapaControllerValidator.setMapaServiceImpl(mapaService);
	}
	
	@Test
	public void deveValidarQuandoEnviadoMapaNaoEncontrado(){
		try{
			mapaControllerValidator.validar(null);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.ITEM_NAO_ENCONTRADO, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.NOT_FOUND, e.getHttpStatus());
		}
	}
	
	@Test
	public void naoDeveValidarBuscarMapa(){
		try{
			mapaControllerValidator.validar(new MapaJSON());
		}catch(APIException e){
			Assert.fail();
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoMapaNulo(){
		try{
			mapaControllerValidator.validarPost(null);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.ITEM_INVALIDO, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoMapaComNomeNulo(){
		try{
			mapaControllerValidator.validarPost(new MapaJSON());
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.MAPA_NOME_INVALIDO, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, e.getHttpStatus());
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoMapaJaCadastrado(){
		try{
			Mockito.when(mapaService.getMapaByNome(Mockito.anyString())).thenReturn(new Mapa());
			mapaControllerValidator.validarPost(new MapaJSON("teste"));
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.MAPA_JA_CADASTRADO, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, e.getHttpStatus());
		}
	}

	@Test
	public void deveValidarQuandoEnviadoMapaComRotasVazias(){
		try{
			mapaControllerValidator.validarPost(new MapaJSON("mapa"));
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.MAPA_ROTAS_INVALIDAS, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, e.getHttpStatus());
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoMapaComRotasInvalida(){
		try{
			MapaJSON mapa = new MapaJSON("mapa");
			RotaJSON rotaNula = null;
			mapa.setRotas(Arrays.asList(rotaNula));
			mapaControllerValidator.validarPost(mapa);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.ROTA_INVALIDA, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, e.getHttpStatus());
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoMapaComRotasComOrigemInvalida(){
		try{
			MapaJSON mapa = new MapaJSON("mapa");
			RotaJSON rotaNula = new RotaJSON();
			mapa.setRotas(Arrays.asList(rotaNula));
			mapaControllerValidator.validarPost(mapa);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.ROTA_ORIGEM_INVALIDA, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, e.getHttpStatus());
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoMapaComRotasComDestinoInvalida(){
		try{
			MapaJSON mapa = new MapaJSON("mapa");
			RotaJSON rotaNula = new RotaJSON("origem","",0);
			mapa.setRotas(Arrays.asList(rotaNula));
			mapaControllerValidator.validarPost(mapa);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.ROTA_DESTINO_INVALIDO, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, e.getHttpStatus());
		}
	}

	@Test
	public void deveValidarQuandoEnviadoMapaComRotasComDistanciaInvalida(){
		try{
			MapaJSON mapa = new MapaJSON("mapa");
			RotaJSON rotaNula = new RotaJSON("origem","destino",0);
			mapa.setRotas(Arrays.asList(rotaNula));
			mapaControllerValidator.validarPost(mapa);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.ROTA_DISTANCIA_INVALIDA, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, e.getHttpStatus());
		}
	}
	
	@Test
	public void naoDeveValidarQuandoEnviadoMapa(){
		try{
			MapaJSON mapa = new MapaJSON("mapa");
			RotaJSON rotaNula = new RotaJSON("origem","destino",1);
			mapa.setRotas(Arrays.asList(rotaNula));
			mapaControllerValidator.validarPost(mapa);	
		}catch(APIException e){
			Assert.fail();
		}
	}
	
}
