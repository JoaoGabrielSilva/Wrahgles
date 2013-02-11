package br.com.wrahgles.dao;

import java.util.List;

import br.com.wrahgles.model.LocalizacaoFoto;

public interface LocalizacaoFotoDAO extends GenericoDAO<LocalizacaoFoto, Long>{

	public boolean existeNomeImagem(Long idLocalizacao, String imagem);
	public List<LocalizacaoFoto> findByAprovado(int qtdResult, int page, boolean fllgAtivo);
	public int countByAprovado(boolean flgAtivo);
	public LocalizacaoFoto findByIdLocalPrimeiraFoto(Long idLocal);
	public List<LocalizacaoFoto> findByIdUsuario(Long idUsuario, int qtdResult);
	public List<LocalizacaoFoto> findByIdLocalizacao(Long idLocalizacao);
	public int countByIdUsuario(Long idUsuario, boolean flgAtivo);
	public int countByIdLocalizacao(Long idLocalizacao, boolean flgAtivo);
}
