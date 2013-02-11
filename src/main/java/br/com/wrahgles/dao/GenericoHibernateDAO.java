package br.com.wrahgles.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;




public class GenericoHibernateDAO<T, ID extends Serializable> implements GenericoDAO<T, ID> {

	private Class<T> persistentClass;
	private final Session session;
	
	
	@SuppressWarnings("unchecked")
	public GenericoHibernateDAO(Session session){
		this.session = session;
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public Class<T> getPersistentClass(){
		return persistentClass;
	}
	
	@SuppressWarnings("unchecked")
	public T findById(ID id){
		T entity;
		entity = (T) session.get(getPersistentClass(), id);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(){
		Criteria crit = session.createCriteria(getPersistentClass());
		return crit.list();
	}
	
	public void saveOrUpdate(T entity) {
		session.clear();
		session.saveOrUpdate(entity);
	}
	
	@Override
	public void save(T entity) {
        session.save(entity);
		
	}
	@Override
	public void update(T entity) {
		session.update(entity);
	}
	@Override
	public void delete(T entity) {
		session.delete(entity);
	}
	
	@Override
	public int count() {
		Criteria crit = session.createCriteria(getPersistentClass());
		crit.setProjection(Projections.rowCount());
		
		Object obj = crit.uniqueResult();
		if(obj == null)
			return 0;
		
		return (Integer) obj;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(int qtdResult, int page) {
      Criteria crit = session.createCriteria(getPersistentClass());
      crit.setMaxResults(qtdResult);
      crit.setFirstResult((qtdResult * (page +1)) - qtdResult);
		
		return crit.list();
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
