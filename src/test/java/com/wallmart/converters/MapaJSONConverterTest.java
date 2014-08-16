package com.wallmart.converters;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.wallmart.model.json.MapaJSON;
import com.wallmart.model.json.RotaJSON;
import com.wallmart.model.mapa.Mapa;
import com.wallmart.model.mapa.Rota;

public class MapaJSONConverterTest {

	MapaJSONConverter mapaJSONConverter = new MapaJSONConverter();
	
	@Test
	public void deveConverterListaDeMapaParaListaDeJSON(){
		List<Mapa> mapas = getMapas();
		List<MapaJSON> listJSON = mapaJSONConverter.converToListJSON(mapas);
		Assert.assertEquals(listJSON.size(), mapas.size());
		assertTests(mapas.get(0), listJSON.get(0));
	}
	
	private void assertTests(Mapa mapa, MapaJSON mapaJSON) {
		Assert.assertEquals(mapaJSON.getId(), mapa.getId());
		Assert.assertEquals(mapaJSON.getNome(), mapa.getNome());
		Assert.assertEquals(mapaJSON.getRotas().size(), mapa.getRotas().size());
		for (int i = 0; i < mapa.getRotas().size(); i++) {
			assertRotaTest(mapaJSON.getRotas().get(i),mapa.getRotas().get(i));	
		}
	}

	private void assertRotaTest(RotaJSON rotaJSON, Rota rota) {
		Assert.assertEquals(rotaJSON.getOrigem(), rota.getOrigem());
		Assert.assertEquals(rotaJSON.getDestino(), rota.getDestino());
		Assert.assertEquals(rotaJSON.getDistancia().intValue(), rota.getDistancia().intValue());
	}

	@Test
	public void deveConverterMapaJSONParaMapa(){
		MapaJSON mapaJSON = getMapaJSON();
		Mapa mapa = mapaJSONConverter.convertToModel(mapaJSON);
		assertTests(mapa, mapaJSON);
	}

	private MapaJSON getMapaJSON() {
		MapaJSON mapaJSON = new MapaJSON(2L,"mapa");
		RotaJSON rotaNula = new RotaJSON("teste","teste1",1);
		mapaJSON.setRotas(Arrays.asList(rotaNula));
		return mapaJSON;
	}

	private List<Mapa> getMapas() {
		Mapa mapa = new Mapa(2L,"mapa");
		Rota rotaNula = new Rota("teste2","teste3",3);
		mapa.setRotas(Arrays.asList(rotaNula));		
		return Arrays.asList(mapa);
	}
}
