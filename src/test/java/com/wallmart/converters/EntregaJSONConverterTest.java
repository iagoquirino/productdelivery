package com.wallmart.converters;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.wallmart.model.vo.EntregaVO;
import com.wallmart.model.vo.PontoVO;
import com.wallmart.rest.json.EntregaJSON;
import com.wallmart.rest.json.PontoJSON;

public class EntregaJSONConverterTest {

	EntregaJSONConverter entregaJSONConverter = new EntregaJSONConverter();

	@Test
	public void deveConverterParaEntregaJSON()
	{
		EntregaVO entrega = getEntrega();
		EntregaJSON entregaJSON = entregaJSONConverter.convertToJSON(entrega);
		Assert.assertEquals(entrega.getCusto()+"", entrega.getCusto()+"");
		Assert.assertEquals(entrega.getPontos().size(), entregaJSON.getPontos().size());
		assertPontos(entrega.getPontos().get(0), entregaJSON.getPontos().get(0));
		assertPontos(entrega.getPontos().get(1), entregaJSON.getPontos().get(1));
	}

	private void assertPontos(PontoVO ponto, PontoJSON pontoJSON) {
		Assert.assertEquals(ponto.getNome(), pontoJSON.getNome());
	}

	private EntregaVO getEntrega() {
		return new EntregaVO(2.9, getPontos());
	}

	private List<PontoVO> getPontos() {
		return Arrays.asList(new PontoVO("teste"),new PontoVO("teste2"));
	}
	
}
