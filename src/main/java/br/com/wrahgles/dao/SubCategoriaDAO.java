package br.com.wrahgles.dao;

import java.util.List;

import br.com.wrahgles.model.SubCategoria;

public interface SubCategoriaDAO extends GenericoDAO<SubCategoria, Long>{
	
	public SubCategoria findSubCategoriaByNome(SubCategoria subCategoria);
	public List<SubCategoria> findByNomeILike(String nome);

}
