package com.wallmart.repository;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wallmart.model.delivery.Map;
import com.wallmart.repository.interfaces.IMapRepository;

@Repository
@Transactional
public class MapRepository extends BaseRepositoryHibernate<Map> implements IMapRepository{

	@Override
	protected Class<Map> getEntityClass() {
		return Map.class;
	}

	public Map find(String name) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		detachedCriteria.add(Restrictions.eq("name", name));
		return findByOneCriteria(detachedCriteria);
	}

}
