package com.wallmart.service.validator;

import org.junit.Assert;
import org.junit.Test;

import com.wallmart.constants.Constants;
import com.wallmart.exception.EntregaMercadoriaException;
import com.wallmart.model.mapa.Mapa;

public class EntregaServiceValidatorTest {

	private static final Mapa MAPA = new Mapa();
	private static final double VALOR_COMBUSTIVEL = 2.2;
	private static final int AUTONOMIA = 1;
	private static final String DESTINO = "DESTINO";
	private static final String ORIGEM = "ORIGEM";
	
	EntregaServiceValidator entregaServiceValidator = new EntregaServiceValidator();
	
	@Test
	public void deveValidarQuandoEnviadoUmMapaInvalido(){
		try{
			entregaServiceValidator.validar(null,ORIGEM, DESTINO, AUTONOMIA, VALOR_COMBUSTIVEL);
			Assert.fail();
		}catch(EntregaMercadoriaException e)
		{
			Assert.assertEquals(Constants.ITEM_NAO_ENCONTRADO, e.getMensagem());
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoOrigemInvalida(){
		try{
			entregaServiceValidator.validar(MAPA,"", DESTINO, AUTONOMIA, VALOR_COMBUSTIVEL);
			Assert.fail();
		}catch(EntregaMercadoriaException e)
		{
			Assert.assertEquals(Constants.ROTA_ORIGEM_INVALIDA, e.getMensagem());
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoDestinoInvalido(){
		try{
			entregaServiceValidator.validar(MAPA,ORIGEM, "", AUTONOMIA, VALOR_COMBUSTIVEL);
			Assert.fail();
		}catch(EntregaMercadoriaException e)
		{
			Assert.assertEquals(Constants.ROTA_DESTINO_INVALIDO, e.getMensagem());
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoAutonomiaInvalida(){
		try{
			entregaServiceValidator.validar(MAPA,ORIGEM, DESTINO, 0, VALOR_COMBUSTIVEL);
			Assert.fail();
		}catch(EntregaMercadoriaException e)
		{
			Assert.assertEquals(Constants.AUTONOMIA_INVALIDA, e.getMensagem());
		}
	}
	
	@Test
	public void deveValidarQuandoEnviadoValorGasolinaInvalida(){
		try{
			entregaServiceValidator.validar(MAPA,ORIGEM, DESTINO, AUTONOMIA, -1.0);
			Assert.fail();
		}catch(EntregaMercadoriaException e)
		{
			Assert.assertEquals(Constants.VALOR_COMBUSTIVEL_INVALIDO, e.getMensagem());
		}
	}

	@Test
	public void naoDeveValidar() throws EntregaMercadoriaException{
		entregaServiceValidator.validar(MAPA,ORIGEM, DESTINO, AUTONOMIA, VALOR_COMBUSTIVEL);
	}
	
}
