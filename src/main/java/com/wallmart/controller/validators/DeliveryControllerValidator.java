package com.wallmart.controller.validators;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.wallmart.constants.Constants;
import com.wallmart.exception.APIException;

@Component
public class DeliveryControllerValidator {

	public void validate(String origem, String destino, Integer autonomia,Double valorCombustivel) {
		if(isBlank(origem)){
			throw new APIException(Constants.INVALID_ORIGIN_ROUTE, HttpStatus.BAD_REQUEST);
		}
		if(isBlank(destino)){
			throw new APIException(Constants.INVALID_DESTINATION_ROUTE, HttpStatus.BAD_REQUEST);
		}
		if(autonomia == null || autonomia.intValue() <= 0){
			throw new APIException(Constants.INVALID_AUTONOMY, HttpStatus.BAD_REQUEST);
		}
		if(valorCombustivel == null || valorCombustivel.doubleValue() < 0){
			throw new APIException(Constants.INVALID_GAS_COST, HttpStatus.BAD_REQUEST);
		}
	}

	private boolean isBlank(String string){
		return string == null || string.isEmpty();
	}
}
