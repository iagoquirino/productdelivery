package com.wallmart.repository.interfaces;

import com.wallmart.model.delivery.Map;

public interface IMapRepository extends IBaseRepositoryHibernate<Map> {

	Map find(String name);

}
