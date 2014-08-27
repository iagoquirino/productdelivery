package com.wallmart.repository;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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

	public Mapa buscar(String nome) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		detachedCriteria.add(Restrictions.eq("nome", nome));
		return findByOneCriteria(detachedCriteria);
	}

}
