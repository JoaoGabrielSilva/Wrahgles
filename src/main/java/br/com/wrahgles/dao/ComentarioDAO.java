package br.com.wrahgles.dao;

import java.util.List;

import br.com.wrahgles.bean.query.ComentarioQuery;
import br.com.wrahgles.model.Comentario;

public interface ComentarioDAO extends GenericoDAO<Comentario, Long>{
	
	public List<Comentario> findComentarioByLocalizacaoId(Long idLocalizacao);
	public List<ComentarioQuery> findComentarioByUsuarioId(Long idUsuario);
	public int countComentarioByUsuarioId(Long idUsuario);
	public Comentario findComentarioByUsuarioAndLocalizacao(Long idUsuario, Long idLocalizacao);
	public int countComentarioByLocalizacao(Long idLocalizacao);
	public int sumNotasComentariosByLocalizacao(Long idLocalizacao);
	public int countByAprovado(boolean flgAtivo);
	public List<Comentario> findForPaginationByAprovado(int qtdResult, int page, boolean flgAtivo);
	public Comentario findUltimoComentarioByIdUsuario(Long idUsuario);
	

}
