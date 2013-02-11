package br.com.wrahgles.dao;

import br.com.wrahgles.model.Denuncia;

public interface DenunciaDAO extends GenericoDAO<Denuncia, Long> {

	public Denuncia findDenunciaByUsuarioAndLocalizacao(Long idUsuario, Long idLocalizacao);
}
