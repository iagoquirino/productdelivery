package com.wallmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.wallmart.controller.validators.DeliveryControllerValidator;
import com.wallmart.converters.DeliveryJSONConverter;
import com.wallmart.exception.ProductDeliveryException;
import com.wallmart.model.delivery.Map;
import com.wallmart.model.vo.DeliveryVO;
import com.wallmart.rest.json.DeliveryJSON;
import com.wallmart.service.interfaces.IDeliveryService;
import com.wallmart.service.interfaces.IMapService;

@Controller
@RequestMapping(value = "deliveries")
public class DeliveryController extends APIController {

	@Autowired
	private DeliveryControllerValidator deliveryValidator;
	
	@Autowired
	private IDeliveryService deliveryService;
	
	@Autowired
	private IMapService mapService;
	
	@Autowired 
	private DeliveryJSONConverter deliveryJSONConverter;
	
	@RequestMapping(value = "/{idMap}/routes", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public String getRoutes(@PathVariable(value = "idMap")Long idMap,@RequestParam(value = "origin")String origin,@RequestParam(value = "destination")String destination,@RequestParam(value = "autonomy")Integer autonomy,@RequestParam(value = "gasCost")Double gasCost) throws ProductDeliveryException{
		deliveryValidator.validate(origin, destination, autonomy, gasCost);
		Map map = mapService.find(idMap);
		DeliveryVO entrega = deliveryService.calculateRoutes(map, origin, destination, autonomy, gasCost);
		DeliveryJSON deliveryJSON = deliveryJSONConverter.convertToJSON(entrega);
		return toJSON(deliveryJSON);
	}
	
	@RequestMapping(value = "/routes", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public String getRoutes(@RequestParam(value = "map")String mapName,@RequestParam(value = "origin")String origin,@RequestParam(value = "destination")String destination,@RequestParam(value = "autonomy")Integer autonomy,@RequestParam(value = "gasCost")Double gasCost) throws ProductDeliveryException{
		deliveryValidator.validate(origin, destination, autonomy, gasCost);
		Map map = mapService.find(mapName);
		DeliveryVO delivery = deliveryService.calculateRoutes(map, origin, destination, autonomy, gasCost);
		DeliveryJSON deliveryJSON = deliveryJSONConverter.convertToJSON(delivery);
		return toJSON(deliveryJSON);
	}

	public void setDeliveryValidator(DeliveryControllerValidator deliveryValidator) {
		this.deliveryValidator = deliveryValidator;
	}
	
	public void setDeliveryJSONConverter(
			DeliveryJSONConverter deliveryJSONConverter) {
		this.deliveryJSONConverter = deliveryJSONConverter;
	}
	
	public void setMapService(IMapService mapService) {
		this.mapService = mapService;
	}
	
	public void setDeliveryService(IDeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}
}
