package com.wallmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wallmart.constants.Constants;
import com.wallmart.exception.ProductDeliveryException;
import com.wallmart.model.delivery.Map;
import com.wallmart.repository.interfaces.IMapRepository;
import com.wallmart.service.interfaces.IMapService;

@Service
@Transactional
public class MapServiceImpl implements IMapService {
	
	@Autowired
	private IMapRepository mapaRepository;
	
	public Long save(Map map){
		return mapaRepository.salvar(map).getId();
	}
	
	public Map find(Long id) throws ProductDeliveryException{
		Map map = mapaRepository.buscar(id);
		if(map == null){
			throw new ProductDeliveryException(Constants.ITEN_NOT_FOUND);
		}
		return map;
	}
	
	public List<Map> list(){
		return mapaRepository.listar();
	}
	
	public void delete(Map map)
	{
		mapaRepository.deletar(map);
	}
	
	public Map find(String name) {
		return mapaRepository.find(name);
	}

	
	public void setMapaRepository(IMapRepository mapaRepository) {
		this.mapaRepository = mapaRepository;
	}

}
