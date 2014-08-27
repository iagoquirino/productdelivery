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
import com.wallmart.service.interfaces.IMapaService;

@Component
public class MapaControllerValidator {
	
	private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private IMapaService mapaService;

	public void validar(MapaJSON mapaJSON)
	{
		if(mapaJSON == null){
			throw new APIException(Constants.ITEM_INVALIDO, BAD_REQUEST);
		}
		
		if(isBlank(mapaJSON.getNome())){
			throw new APIException(Constants.MAPA_NOME_INVALIDO, BAD_REQUEST);
		}
		Mapa mapa = mapaService.buscar(mapaJSON.getNome());
		if(mapa != null){
			throw new APIException(Constants.MAPA_JA_CADASTRADO, BAD_REQUEST);
		}
		validarRotas(mapaJSON.getRotas());
	}

	private void validarRotas(List<RotaJSON> rotasJSON) {
		if(rotasJSON == null || rotasJSON.isEmpty()){
			throw new APIException(Constants.MAPA_ROTAS_INVALIDAS, BAD_REQUEST);
		}
		List<String> rotaRepetida = new ArrayList<String>();
		
		for(RotaJSON rotaJSON : rotasJSON){
			if(rotaJSON == null){
				throw new APIException(Constants.ROTA_INVALIDA, BAD_REQUEST);	
			}
			if(isBlank(rotaJSON.getOrigem())){
				throw new APIException(Constants.ROTA_ORIGEM_INVALIDA, BAD_REQUEST);
			}
			if(isBlank(rotaJSON.getDestino())){
				throw new APIException(Constants.ROTA_DESTINO_INVALIDO, BAD_REQUEST);
			}
			if(rotaJSON.getDistancia() <= 0){
				throw new APIException(Constants.ROTA_DISTANCIA_INVALIDA, BAD_REQUEST);
			}
			String key = rotaJSON.getOrigem()+"|"+rotaJSON.getDestino();
			if(rotaRepetida.contains(key)){
				throw new APIException(Constants.MAPA_ROTA_REPETIDA, BAD_REQUEST);
			}else{
				rotaRepetida.add(key);
			}
		}
	}
	
	private boolean isBlank(String string){
		return string == null || string.isEmpty();
	}
	
	public void setMapaService(IMapaService mapaService) {
		this.mapaService = mapaService;
	}
}
