package br.com.wrahgles.dao;

import java.util.List;

import br.com.wrahgles.model.Cidade;

public interface CidadeDAO extends GenericoDAO<Cidade, Long> {

	public List<Cidade> findByIdEstadoAndNomeILike(Long idEstado,String nome);
	
}
