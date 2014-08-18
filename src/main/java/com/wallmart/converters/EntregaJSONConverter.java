package com.wallmart.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wallmart.model.vo.EntregaVO;
import com.wallmart.model.vo.PontoVO;
import com.wallmart.rest.json.EntregaJSON;
import com.wallmart.rest.json.PontoJSON;

@Component
public class EntregaJSONConverter {

	public EntregaJSON convertToJSON(EntregaVO entrega) {
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

	private List<PontoJSON> convertPontosToPontosJSON(List<PontoVO> pontos) {
		List<PontoJSON> pontosListJSON = new ArrayList<PontoJSON>();
		if(pontos != null){
			for(PontoVO ponto : pontos){
				if(ponto != null){
					PontoJSON pontoJSON = new PontoJSON(ponto.getNome());
					pontosListJSON.add(pontoJSON);
				}
			}
		}
		return pontosListJSON;
	}

}
