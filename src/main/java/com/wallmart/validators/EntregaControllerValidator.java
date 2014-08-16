package com.wallmart.validators;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.wallmart.constants.Constants;
import com.wallmart.exception.APIException;

@Component
public class EntregaControllerValidator {

	public void validar(String origem, String destino, Integer autonomia,Double valorCombustivel) {
		if(StringUtils.isBlank(origem)){
			throw new APIException(Constants.ROTA_ORIGEM_INVALIDA, HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(destino)){
			throw new APIException(Constants.ROTA_DESTINO_INVALIDO, HttpStatus.BAD_REQUEST);
		}
		if(autonomia == null || autonomia.intValue() <= 0){
			throw new APIException(Constants.AUTONOMIA_INVALIDA, HttpStatus.BAD_REQUEST);
		}
		if(valorCombustivel == null || valorCombustivel.doubleValue() < 0){
			throw new APIException(Constants.VALOR_COMBUSTIVEL_INVALIDO, HttpStatus.BAD_REQUEST);
		}
	}

}
