package br.com.wrahgles.dao;

import java.util.List;

import br.com.wrahgles.bean.query.VisitaLocalizacaoQuery;
import br.com.wrahgles.model.UsuarioLocalizacaoPK;
import br.com.wrahgles.model.VisitaLocalizacao;

public interface VisitaLocalizacaoDAO {

	public void saveOrUpdate(VisitaLocalizacao visitaLocalizacao);
	public void save(VisitaLocalizacao visitaLocalizacao);
	public void update(VisitaLocalizacao visitaLocalizacao);
	public void delete(VisitaLocalizacao visitaLocalizacao);
	public VisitaLocalizacao findById(UsuarioLocalizacaoPK id);
	public List<VisitaLocalizacaoQuery> findByIdLocalizacao(Long idLocalizacao, int  qtdResult);
	public int countByIdLocalizacao(Long idLocalizacao);
	
}
