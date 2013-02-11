package br.com.wrahgles.dao;

import java.util.List;

import br.com.wrahgles.model.Categoria;

public interface CategoriaDAO extends GenericoDAO<Categoria, Long>{
	
	Categoria findCategoriaByNome(Categoria categoria);
	List<Categoria> findAllOrderName();

}
