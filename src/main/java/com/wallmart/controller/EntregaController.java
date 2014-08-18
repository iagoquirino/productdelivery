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

import com.wallmart.controller.validators.EntregaControllerValidator;
import com.wallmart.converters.EntregaJSONConverter;
import com.wallmart.exception.EntregaMercadoriaException;
import com.wallmart.model.Entrega;
import com.wallmart.model.json.EntregaJSON;
import com.wallmart.model.mapa.Mapa;
import com.wallmart.service.EntregaServiceImpl;
import com.wallmart.service.MapaServiceImpl;

@Controller
@RequestMapping(value = "entrega")
public class EntregaController extends APIController {

	@Autowired
	private EntregaControllerValidator entregaControllerValidator;
	
	@Autowired
	private EntregaServiceImpl entregaService;
	
	@Autowired
	private MapaServiceImpl mapaService;
	
	@Autowired 
	private EntregaJSONConverter entregaJSONConverter;
	
	@RequestMapping(value = "/{idMapa}/definir-rota", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public String definirRota(@PathVariable(value = "idMapa")Long idMapa,@RequestParam(value = "origem")String origem,@RequestParam(value = "destino")String destino,@RequestParam(value = "autonomia")Integer autonomia,@RequestParam(value = "valorCombustivel")Double valorCombustivel) throws EntregaMercadoriaException{
		entregaControllerValidator.validar(origem, destino, autonomia, valorCombustivel);
		Mapa mapa = mapaService.buscarPorId(idMapa);
		Entrega entrega = entregaService.calcularRota(mapa, origem, destino, autonomia, valorCombustivel);
		EntregaJSON entregaJSON = entregaJSONConverter.convertToJSON(entrega);
		return toJSON(entregaJSON);
	}
	
	public void setEntregaControllerValidator(
			EntregaControllerValidator entregaControllerValidator) {
		this.entregaControllerValidator = entregaControllerValidator;
	}
	
	public void setEntregaService(EntregaServiceImpl entregaService) {
		this.entregaService = entregaService;
	}
	
	public void setEntregaJSONConverter(
			EntregaJSONConverter entregaJSONConverter) {
		this.entregaJSONConverter = entregaJSONConverter;
	}
	
	public void setMapaService(MapaServiceImpl mapaService) {
		this.mapaService = mapaService;
	}
}
