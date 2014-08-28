package com.wallmart.controller.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.wallmart.constants.Constants;
import com.wallmart.exception.APIException;
import com.wallmart.model.delivery.Map;
import com.wallmart.rest.json.MapJSON;
import com.wallmart.rest.json.RouteJSON;
import com.wallmart.service.MapServiceImpl;
import com.wallmart.service.interfaces.IMapService;

@Component
public class MapControllerValidator {
	
	private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private IMapService mapaService;

	public void validate(MapJSON mapaJSON)
	{
		if(mapaJSON == null){
			throw new APIException(Constants.ITEN_INVALID, BAD_REQUEST);
		}
		
		if(isBlank(mapaJSON.getName())){
			throw new APIException(Constants.INVALID_MAP_NAME, BAD_REQUEST);
		}
		Map mapa = mapaService.find(mapaJSON.getName());
		if(mapa != null){
			throw new APIException(Constants.ALREADY_REGISTERED_MAP, BAD_REQUEST);
		}
		validarRotas(mapaJSON.getRoutes());
	}

	private void validarRotas(List<RouteJSON> rotasJSON) {
		if(rotasJSON == null || rotasJSON.isEmpty()){
			throw new APIException(Constants.INVALID_ROUTES_MAP, BAD_REQUEST);
		}
		List<String> rotaRepetida = new ArrayList<String>();
		
		for(RouteJSON rotaJSON : rotasJSON){
			if(rotaJSON == null){
				throw new APIException(Constants.INVALID_ROUTE, BAD_REQUEST);	
			}
			if(isBlank(rotaJSON.getOrigin())){
				throw new APIException(Constants.INVALID_ORIGIN_ROUTE, BAD_REQUEST);
			}
			if(isBlank(rotaJSON.getDestination())){
				throw new APIException(Constants.INVALID_DESTINATION_ROUTE, BAD_REQUEST);
			}
			if(rotaJSON.getDistance() <= 0){
				throw new APIException(Constants.INVALID_DISTANCE_ROUTE, BAD_REQUEST);
			}
			String key = rotaJSON.getOrigin()+"|"+rotaJSON.getDestination();
			if(rotaRepetida.contains(key)){
				throw new APIException(Constants.REPEATED_MAP, BAD_REQUEST);
			}else{
				rotaRepetida.add(key);
			}
		}
	}
	
	private boolean isBlank(String string){
		return string == null || string.isEmpty();
	}
	
	public void setMapService(IMapService mapService) {
		this.mapaService = mapService;
	}
}
