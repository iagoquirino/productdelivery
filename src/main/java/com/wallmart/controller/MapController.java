package com.wallmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.gson.Gson;
import com.wallmart.constants.Constants;
import com.wallmart.controller.validators.MapControllerValidator;
import com.wallmart.converters.MapJSONConverter;
import com.wallmart.exception.ProductDeliveryException;
import com.wallmart.rest.json.MapJSON;
import com.wallmart.rest.json.MessageJSON;
import com.wallmart.service.interfaces.IMapService;

@Controller
@RequestMapping(value = "maps")
public class MapController extends APIController {
	
	@Autowired
	private IMapService mapService;
	@Autowired
	private MapJSONConverter mapJSONConverter;
	@Autowired
	private MapControllerValidator mapValidator;

	@RequestMapping(value = "/", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public String list(){
		List<MapJSON> listJSON = mapJSONConverter.converToListJSON(mapService.list());
		return toJSON(listJSON);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public String find(@PathVariable(value = "id")Long id) throws ProductDeliveryException{
		MapJSON mapJSON = mapJSONConverter.convertToJSON(mapService.find(id));
		return toJSON(mapJSON);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public String save(@RequestBody String json){
		MapJSON mapJSON = new Gson().fromJson(json, MapJSON.class);
		mapValidator.validate(mapJSON);
		Long id = mapService.save(mapJSONConverter.convertToModel(mapJSON));
		return toJSON(new MessageJSON(id,Constants.SUCESS));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public String delete(@PathVariable(value = "id") Long id) throws ProductDeliveryException{
		mapService.delete(mapService.find(id));
		return toJSON(new MessageJSON(Constants.SUCESS));
	}
	
	public void setMapJSONConverter(MapJSONConverter mapJSONConverter) {
		this.mapJSONConverter = mapJSONConverter;
	}
	
	public void setMapValidator(MapControllerValidator mapValidator) {
		this.mapValidator = mapValidator;
	}
	
	public void setMapService(IMapService mapService) {
		this.mapService = mapService;
	}
	
}
