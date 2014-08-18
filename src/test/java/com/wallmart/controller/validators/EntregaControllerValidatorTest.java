package com.wallmart.controller.validators;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.wallmart.constants.Constants;
import com.wallmart.exception.APIException;

public class EntregaControllerValidatorTest {

	private static final double VALOR_COMBUSTIVEL_INVALIDA = -1.0;

	private static final int AUTONOMIA = 1;

	private static final double VALOR_COMBUSTIVEL = 2.2;

	private static final String ORIGEM = "ORIGEM";

	private static final String DESTINO = "DESTINO";
	
	EntregaControllerValidator entregaControllerValidator = new EntregaControllerValidator();
	
	@Test
	public void deveValidarQuandoEnviadoOrigemInvalida(){
		try{
			entregaControllerValidator.validar("", DESTINO, AUTONOMIA, VALOR_COMBUSTIVEL);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.ROTA_ORIGEM_INVALIDA);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoOrigemNulo(){
		try{
			entregaControllerValidator.validar(null, DESTINO, AUTONOMIA, VALOR_COMBUSTIVEL);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.ROTA_ORIGEM_INVALIDA);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoDestinoInvalido(){
		try{
			entregaControllerValidator.validar(ORIGEM, "", AUTONOMIA, VALOR_COMBUSTIVEL);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.ROTA_DESTINO_INVALIDO);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoDestinoNulo(){
		try{
			entregaControllerValidator.validar(ORIGEM, null, AUTONOMIA, VALOR_COMBUSTIVEL);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.ROTA_DESTINO_INVALIDO);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoAutonomiaInvalida(){
		try{
			entregaControllerValidator.validar(ORIGEM, DESTINO, 0, VALOR_COMBUSTIVEL);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.AUTONOMIA_INVALIDA);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoAutonomiaNula(){
		try{
			entregaControllerValidator.validar(ORIGEM, DESTINO, null, VALOR_COMBUSTIVEL);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.AUTONOMIA_INVALIDA);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}

	@Test
	public void deveValidarQuandoEnviadoValorCombustivelInvalido(){
		try{
			entregaControllerValidator.validar(ORIGEM, DESTINO, AUTONOMIA, VALOR_COMBUSTIVEL_INVALIDA);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.VALOR_COMBUSTIVEL_INVALIDO);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoValorCombustivelNulo(){
		try{
			entregaControllerValidator.validar(ORIGEM, DESTINO, AUTONOMIA, VALOR_COMBUSTIVEL_INVALIDA);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.VALOR_COMBUSTIVEL_INVALIDO);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Test
	public void naoDeveValidarQuandoEnviadoParametros(){
		entregaControllerValidator.validar(ORIGEM, DESTINO, AUTONOMIA, 0.0);
	}

	

	
	
}
