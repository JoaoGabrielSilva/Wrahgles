package br.com.wrahgles.dao;

import java.util.List;

import br.com.wrahgles.model.Estado;

public interface EstadoDAO extends GenericoDAO<Estado, Long>{

	public List<Estado> findAllOrderNome();
	public Estado findBySigla(String sigla);
	
}
