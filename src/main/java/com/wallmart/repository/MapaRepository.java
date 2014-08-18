package com.wallmart.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wallmart.model.entrega.Mapa;
import com.wallmart.repository.interfaces.IMapaRepository;

@Repository
@Transactional
public class MapaRepository extends BaseRepositoryHibernate<Mapa> implements IMapaRepository{

	@Override
	protected Class<Mapa> getEntityClass() {
		return Mapa.class;
	}

}
