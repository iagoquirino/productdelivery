package com.wallmart.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wallmart.model.entrega.Rota;
import com.wallmart.repository.interfaces.IRotaRepository;

@Repository
@Transactional
public class RotaRepository extends BaseRepositoryHibernate<Rota> implements IRotaRepository{

	protected Class<Rota> getEntityClass() {
		return Rota.class;
	}

}
