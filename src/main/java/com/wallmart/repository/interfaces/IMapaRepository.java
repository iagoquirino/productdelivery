package com.wallmart.repository.interfaces;

import com.wallmart.model.entrega.Mapa;

public interface IMapaRepository extends IBaseRepositoryHibernate<Mapa> {

	Mapa getByNome(String nome);

}
