package com.wallmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.wallmart.constants.Constants;
import com.wallmart.controller.validators.MapaControllerValidator;
import com.wallmart.converters.MapaJSONConverter;
import com.wallmart.model.entrega.Mapa;
import com.wallmart.rest.json.MapaJSON;
import com.wallmart.rest.json.MensagemJSON;
import com.wallmart.service.MapaServiceImpl;

@Controller
@RequestMapping(value = "mapa")
public class MapaController extends APIController {
	
	@Autowired
	private MapaServiceImpl mapaService;
	@Autowired
	private MapaJSONConverter mapaJSONConverter;
	@Autowired
	private MapaControllerValidator mapaValidator;

	@RequestMapping(value = "/", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public String listar(){
		List<MapaJSON> listJSON = mapaJSONConverter.converToListJSON(mapaService.buscarTodos());
		return toJSON(listJSON);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public String buscarMapaPorId(@PathVariable(value = "id")Long id){
		MapaJSON mapaJSON = mapaJSONConverter.convertToJSON(mapaService.buscarPorId(id));
		mapaValidator.validar(mapaJSON);
		return toJSON(mapaJSON);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public String salvar(MapaJSON mapaJSON){
		mapaValidator.validarPost(mapaJSON);
		Long id = mapaService.salvar(mapaJSONConverter.convertToModel(mapaJSON));
		return toJSON(new MensagemJSON(id,Constants.SUCESSO));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public String deletar(Long id){
		Mapa mapa = mapaService.buscarPorId(id);
		MapaJSON mapaJSON = mapaJSONConverter.convertToJSON(mapa);
		mapaValidator.validar(mapaJSON);
		mapaService.deletar(mapa);
		return toJSON(new MensagemJSON(Constants.SUCESSO));
	}
	
	public void setMapaJSONConverter(MapaJSONConverter mapaJSONConverter) {
		this.mapaJSONConverter = mapaJSONConverter;
	}
	
	public void setMapaService(MapaServiceImpl mapaService) {
		this.mapaService = mapaService;
	}
	
	public void setMapaValidator(MapaControllerValidator mapaValidator) {
		this.mapaValidator = mapaValidator;
	}
	
}
