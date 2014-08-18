package com.wallmart.service.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.wallmart.constants.Constants;
import com.wallmart.exception.EntregaMercadoriaException;
import com.wallmart.model.mapa.Mapa;

@Component
public class EntregaServiceValidator {

	public void validar(Mapa mapa, String origem, String destino,Integer autonomia, Double valorGasolina) throws EntregaMercadoriaException {
		if(mapa == null){
			throw new EntregaMercadoriaException(Constants.ITEM_NAO_ENCONTRADO);
		}
		if(StringUtils.isBlank(origem)){
			throw new EntregaMercadoriaException(Constants.ROTA_ORIGEM_INVALIDA);
		}
		if(StringUtils.isBlank(destino)){
			throw new EntregaMercadoriaException(Constants.ROTA_DESTINO_INVALIDO);
		}
		if(autonomia == null || autonomia.intValue() <= 0){
			throw new EntregaMercadoriaException(Constants.AUTONOMIA_INVALIDA);
		}
		if(valorGasolina == null || valorGasolina < 0){
			throw new EntregaMercadoriaException(Constants.VALOR_COMBUSTIVEL_INVALIDO);
		}
	}
}
