package com.wallmart.controller.validators;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.wallmart.constants.Constants;
import com.wallmart.exception.APIException;
import com.wallmart.model.json.MapaJSON;
import com.wallmart.model.json.RotaJSON;

@Component
public class MapaControllerValidator {

	public void validar(MapaJSON mapaJSON)
	{
		if(mapaJSON == null){
			throw new APIException(Constants.ITEM_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
		}
	}

	public void validarPost(MapaJSON mapaJSON)
	{
		if(mapaJSON == null){
			throw new APIException(Constants.ITEM_INVALIDO, HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isBlank(mapaJSON.getNome())){
			throw new APIException(Constants.MAPA_NOME_INVALIDO, HttpStatus.NOT_ACCEPTABLE);
		}
		validarRotas(mapaJSON.getRotas());
	}

	private void validarRotas(List<RotaJSON> rotasJSON) {
		if(rotasJSON == null || rotasJSON.isEmpty()){
			throw new APIException(Constants.MAPA_ROTAS_INVALIDAS, HttpStatus.NOT_ACCEPTABLE);
		}
		for(RotaJSON rotaJSON : rotasJSON){
			if(rotaJSON == null){
				throw new APIException(Constants.ROTA_INVALIDA, HttpStatus.NOT_ACCEPTABLE);	
			}
			if(StringUtils.isBlank(rotaJSON.getOrigem())){
				throw new APIException(Constants.ROTA_ORIGEM_INVALIDA, HttpStatus.NOT_ACCEPTABLE);
			}
			if(StringUtils.isBlank(rotaJSON.getDestino())){
				throw new APIException(Constants.ROTA_DESTINO_INVALIDO, HttpStatus.NOT_ACCEPTABLE);
			}
			if(rotaJSON.getDistancia() <= 0){
				throw new APIException(Constants.ROTA_DISTANCIA_INVALIDA, HttpStatus.NOT_ACCEPTABLE);
			}
		}
	}
}
