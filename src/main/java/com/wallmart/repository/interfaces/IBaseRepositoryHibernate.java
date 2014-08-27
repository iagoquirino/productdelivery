package com.wallmart.repository.interfaces;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

public interface IBaseRepositoryHibernate<T> {
	
	T findOneByCriterion( Criterion... criterion );
	
	List<T> findListByCriterion( Criterion... criterion );
	
	T findByOneCriteria( DetachedCriteria criteria );
	
	List<T> findByCriteria( DetachedCriteria criteria );
	
	void deletar (T entity);
	
	void deleteAll (List<T> listEntities);
	
	T salvar(T t);
	
	T saveOrUpdate(T t);
	
	T merge(T t);
	
	List<T> saveAll(List<T> listT);
	
	T buscar( Long id );
	
	List<T> listar( );
	
	int executeUpdate(String sql);

}
