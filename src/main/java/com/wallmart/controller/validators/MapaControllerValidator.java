package com.wallmart.controller.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.wallmart.constants.Constants;
import com.wallmart.exception.APIException;
import com.wallmart.model.entrega.Mapa;
import com.wallmart.rest.json.MapaJSON;
import com.wallmart.rest.json.RotaJSON;
import com.wallmart.service.MapaServiceImpl;

@Component
public class MapaControllerValidator {
	
	@Autowired
	private MapaServiceImpl mapaServiceImpl;

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
		if(isBlank(mapaJSON.getNome())){
			throw new APIException(Constants.MAPA_NOME_INVALIDO, HttpStatus.NOT_ACCEPTABLE);
		}
		Mapa mapa = mapaServiceImpl.buscarMapaPorNome(mapaJSON.getNome());
		if(mapa != null){
			throw new APIException(Constants.MAPA_JA_CADASTRADO, HttpStatus.NOT_ACCEPTABLE);
		}
		validarRotas(mapaJSON.getRotas());
	}

	private void validarRotas(List<RotaJSON> rotasJSON) {
		if(rotasJSON == null || rotasJSON.isEmpty()){
			throw new APIException(Constants.MAPA_ROTAS_INVALIDAS, HttpStatus.NOT_ACCEPTABLE);
		}
		List<String> rotaRepetida = new ArrayList<String>();
		
		for(RotaJSON rotaJSON : rotasJSON){
			if(rotaJSON == null){
				throw new APIException(Constants.ROTA_INVALIDA, HttpStatus.NOT_ACCEPTABLE);	
			}
			if(isBlank(rotaJSON.getOrigem())){
				throw new APIException(Constants.ROTA_ORIGEM_INVALIDA, HttpStatus.NOT_ACCEPTABLE);
			}
			if(isBlank(rotaJSON.getDestino())){
				throw new APIException(Constants.ROTA_DESTINO_INVALIDO, HttpStatus.NOT_ACCEPTABLE);
			}
			if(rotaJSON.getDistancia() <= 0){
				throw new APIException(Constants.ROTA_DISTANCIA_INVALIDA, HttpStatus.NOT_ACCEPTABLE);
			}
			String key = rotaJSON.getOrigem()+"|"+rotaJSON.getDestino();
			if(rotaRepetida.contains(key)){
				throw new APIException(Constants.MAPA_ROTA_REPETIDA, HttpStatus.NOT_ACCEPTABLE);
			}else{
				rotaRepetida.add(key);
			}
		}
	}
	
	private boolean isBlank(String string){
		return string == null || string.isEmpty();
	}
	
	
	public void setMapaServiceImpl(MapaServiceImpl mapaServiceImpl) {
		this.mapaServiceImpl = mapaServiceImpl;
	}
}
