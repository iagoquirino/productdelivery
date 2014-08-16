package com.wallmart.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wallmart.model.Entrega;
import com.wallmart.model.Ponto;
import com.wallmart.model.json.EntregaJSON;
import com.wallmart.model.json.PontoJSON;

@Component
public class EntregaJSONConverter {

	public EntregaJSON convertToJSON(Entrega entrega) {
		if(entrega == null)
		{
			return null;
		}
		EntregaJSON entregaJSON = new EntregaJSON();
		entregaJSON.setCusto(entrega.getCusto());
		List<PontoJSON> pontosJSON = convertPontosToPontosJSON(entrega.getPontos());
		entregaJSON.setPontos(pontosJSON);
		return entregaJSON;
	}

	private List<PontoJSON> convertPontosToPontosJSON(List<Ponto> pontos) {
		List<PontoJSON> pontosListJSON = new ArrayList<PontoJSON>();
		if(pontos != null){
			for(Ponto ponto : pontos){
				if(ponto != null){
					PontoJSON pontoJSON = new PontoJSON(ponto.getNome());
					pontosListJSON.add(pontoJSON);
				}
			}
		}
		return pontosListJSON;
	}

}
