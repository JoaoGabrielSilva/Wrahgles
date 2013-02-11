package br.com.wrahgles.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericoDAO<T, ID extends Serializable> {
	
	public T findById(ID id);
	public void saveOrUpdate(T entity);
	public void save(T entity);
	public void update(T entity);
	public void delete(T entity);
	public int count();
	public List<T> findAll(int qtdResult, int page);

	public List<T> findAll();
	
	
	

}
