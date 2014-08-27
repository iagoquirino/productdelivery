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
import com.wallmart.model.entrega.Mapa;
import com.wallmart.model.vo.EntregaVO;
import com.wallmart.rest.json.EntregaJSON;
import com.wallmart.service.interfaces.IEntregaService;
import com.wallmart.service.interfaces.IMapaService;

@Controller
@RequestMapping(value = "entrega")
public class EntregaController extends APIController {

	@Autowired
	private EntregaControllerValidator entregaValidator;
	
	@Autowired
	private IEntregaService entregaService;
	
	@Autowired
	private IMapaService mapaService;
	
	@Autowired 
	private EntregaJSONConverter entregaJSONConverter;
	
	@RequestMapping(value = "/{idMapa}/rota", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public String rota(@PathVariable(value = "idMapa")Long idMapa,@RequestParam(value = "origem")String origem,@RequestParam(value = "destino")String destino,@RequestParam(value = "autonomia")Integer autonomia,@RequestParam(value = "valorCombustivel")Double valorCombustivel) throws EntregaMercadoriaException{
		entregaValidator.validar(origem, destino, autonomia, valorCombustivel);
		Mapa mapa = mapaService.buscar(idMapa);
		EntregaVO entrega = entregaService.calcularRota(mapa, origem, destino, autonomia, valorCombustivel);
		EntregaJSON entregaJSON = entregaJSONConverter.convertToJSON(entrega);
		return toJSON(entregaJSON);
	}

	public void setEntregaValidator(EntregaControllerValidator entregaValidator) {
		this.entregaValidator = entregaValidator;
	}
	
	public void setEntregaJSONConverter(
			EntregaJSONConverter entregaJSONConverter) {
		this.entregaJSONConverter = entregaJSONConverter;
	}
	
	public void setMapaService(IMapaService mapaService) {
		this.mapaService = mapaService;
	}
	
	public void setEntregaService(IEntregaService entregaService) {
		this.entregaService = entregaService;
	}
}
