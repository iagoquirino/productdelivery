package com.wallmart.validators;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.wallmart.constants.Constants;
import com.wallmart.exception.APIException;

public class EntregaControllerValidatorTest {

	EntregaControllerValidator entregaControllerValidator = new EntregaControllerValidator();
	
	@Test
	public void deveValidarQuandoEnviadoOrigemInvalida(){
		try{
			entregaControllerValidator.validar("", "DESTINO", 1, 2.2);
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
			entregaControllerValidator.validar(null, "DESTINO", 1, 2.2);
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
			entregaControllerValidator.validar("ORIGEM", "", 1, 2.2);
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
			entregaControllerValidator.validar("ORIGEM", null, 1, 2.2);
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
			entregaControllerValidator.validar("ORIGEM", "DESTINO", 0, 2.2);
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
			entregaControllerValidator.validar("ORIGEM", "DESTINO", null, 2.2);
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
			entregaControllerValidator.validar("ORIGEM", "DESTINO", 1, -1.0);
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
			entregaControllerValidator.validar("ORIGEM", "DESTINO", 1, -1.0);
			Assert.fail();
		}catch(APIException e)
		{
			Assert.assertEquals(e.getMessage(), Constants.VALOR_COMBUSTIVEL_INVALIDO);
			Assert.assertEquals(e.getHttpStatus(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Test
	public void naoDeveValidarQuandoEnviadoParametros(){
		entregaControllerValidator.validar("ORIGEM", "DESTINO", 1, 0.0);
	}

	

	
	
}
