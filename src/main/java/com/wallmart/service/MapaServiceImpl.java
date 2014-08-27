package com.wallmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wallmart.constants.Constants;
import com.wallmart.exception.EntregaMercadoriaException;
import com.wallmart.model.entrega.Mapa;
import com.wallmart.repository.interfaces.IMapaRepository;
import com.wallmart.service.interfaces.IMapaService;

@Service
@Transactional
public class MapaServiceImpl implements IMapaService {
	
	@Autowired
	private IMapaRepository mapaRepository;
	
	public Long salvar(Mapa mapa){
		return mapaRepository.salvar(mapa).getId();
	}
	
	public Mapa buscar(Long id) throws EntregaMercadoriaException{
		Mapa mapa = mapaRepository.buscar(id);
		if(mapa == null){
			throw new EntregaMercadoriaException(Constants.ITEM_NAO_ENCONTRADO);
		}
		return mapa;
	}
	
	public List<Mapa> listar(){
		return mapaRepository.listar();
	}
	
	public void deletar(Mapa mapa)
	{
		mapaRepository.deletar(mapa);
	}
	
	public Mapa buscar(String nome) {
		return mapaRepository.buscar(nome);
	}

	
	public void setMapaRepository(IMapaRepository mapaRepository) {
		this.mapaRepository = mapaRepository;
	}

}
