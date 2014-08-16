package com.wallmart.converters;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.wallmart.model.Entrega;
import com.wallmart.model.Ponto;
import com.wallmart.model.json.EntregaJSON;
import com.wallmart.model.json.PontoJSON;

public class EntregaJSONConverterTest {

	EntregaJSONConverter entregaJSONConverter = new EntregaJSONConverter();

	@Test
	public void deveConverterParaEntregaJSON()
	{
		Entrega entrega = getEntrega();
		EntregaJSON entregaJSON = entregaJSONConverter.convertToJSON(entrega);
		Assert.assertEquals(entrega.getCusto()+"", entrega.getCusto()+"");
		Assert.assertEquals(entrega.getPontos().size(), entregaJSON.getPontos().size());
		assertPontos(entrega.getPontos().get(0), entregaJSON.getPontos().get(0));
		assertPontos(entrega.getPontos().get(1), entregaJSON.getPontos().get(1));
	}

	private void assertPontos(Ponto ponto, PontoJSON pontoJSON) {
		Assert.assertEquals(ponto.getNome(), pontoJSON.getNome());
	}

	private Entrega getEntrega() {
		return new Entrega(2.9, getPontos());
	}

	private List<Ponto> getPontos() {
		return Arrays.asList(new Ponto("teste"),new Ponto("teste2"));
	}
	
}
