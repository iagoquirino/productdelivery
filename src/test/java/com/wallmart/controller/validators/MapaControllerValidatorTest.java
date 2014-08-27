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
	public void deveValidarQuandoEnviadoMapaNulo(){
		try{
			mapaControllerValidator.validar(null);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.ITEM_INVALIDO, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoMapaComNomeNulo(){
		try{
			mapaControllerValidator.validar(new MapaJSON());
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.MAPA_NOME_INVALIDO, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoMapaJaCadastrado(){
		try{
			Mockito.when(mapaService.buscar(Mockito.anyString())).thenReturn(new Mapa());
			mapaControllerValidator.validar(new MapaJSON("teste"));
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.MAPA_JA_CADASTRADO, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}

	@Test
	public void deveValidarQuandoEnviadoMapaComRotasVazias(){
		try{
			mapaControllerValidator.validar(new MapaJSON("mapa"));
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.MAPA_ROTAS_INVALIDAS, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoMapaComRotasInvalida(){
		try{
			MapaJSON mapa = new MapaJSON("mapa");
			RotaJSON rotaNula = null;
			mapa.setRotas(Arrays.asList(rotaNula));
			mapaControllerValidator.validar(mapa);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.ROTA_INVALIDA, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoMapaComRotasComOrigemInvalida(){
		try{
			MapaJSON mapa = new MapaJSON("mapa");
			RotaJSON rotaNula = new RotaJSON();
			mapa.setRotas(Arrays.asList(rotaNula));
			mapaControllerValidator.validar(mapa);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.ROTA_ORIGEM_INVALIDA, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoMapaComRotasComDestinoInvalida(){
		try{
			MapaJSON mapa = new MapaJSON("mapa");
			RotaJSON rotaNula = new RotaJSON("origem","",0);
			mapa.setRotas(Arrays.asList(rotaNula));
			mapaControllerValidator.validar(mapa);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.ROTA_DESTINO_INVALIDO, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}

	@Test
	public void deveValidarQuandoEnviadoMapaComRotasComDistanciaInvalida(){
		try{
			MapaJSON mapa = new MapaJSON("mapa");
			RotaJSON rotaNula = new RotaJSON("origem","destino",0);
			mapa.setRotas(Arrays.asList(rotaNula));
			mapaControllerValidator.validar(mapa);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.ROTA_DISTANCIA_INVALIDA, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoMapaComRotasRepetida(){
		try{
			MapaJSON mapa = new MapaJSON("mapa");
			RotaJSON rotaNula = new RotaJSON("origem","destino",1);
			RotaJSON rotaNula2 = new RotaJSON("origem","destino",2);
			mapa.setRotas(Arrays.asList(rotaNula,rotaNula2));
			mapaControllerValidator.validar(mapa);
			Assert.fail();
		}catch(APIException e){
			Assert.assertEquals(Constants.MAPA_ROTA_REPETIDA, e.getMensagemJSON().getMensagem());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
		}
	}
	
	@Test
	public void naoDeveValidarQuandoEnviadoMapa(){
		try{
			MapaJSON mapa = new MapaJSON("mapa");
			RotaJSON rotaNula = new RotaJSON("origem","destino",1);
			mapa.setRotas(Arrays.asList(rotaNula));
			mapaControllerValidator.validar(mapa);	
		}catch(APIException e){
			Assert.fail();
		}
	}
	
}
