package com.wallmart.service.interfaces;

import java.util.List;

import com.wallmart.exception.EntregaMercadoriaException;
import com.wallmart.model.entrega.Mapa;

public interface IMapaService {

	Mapa buscar(Long id) throws EntregaMercadoriaException;
	
	Mapa buscar(String nome);

	List<Mapa> listar();

	Long salvar(Mapa convertToModel);

	void deletar(Mapa mapa);

}
