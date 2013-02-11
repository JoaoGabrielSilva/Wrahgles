package br.com.wrahgles.dao;

import java.util.List;

import br.com.wrahgles.bean.query.LocalizacaoGroupQuery;
import br.com.wrahgles.bean.query.LocalizacaoQuery;
import br.com.wrahgles.model.Bounds;
import br.com.wrahgles.model.Localizacao;

public interface LocalizacaoDAO extends GenericoDAO<Localizacao, Long>{
	
	public List<LocalizacaoQuery> findLocalizacaoByDescricaoInLatLng(String categoria, Bounds bounds, int qtdResult, int page);
	public List<LocalizacaoQuery> findLocalizacaoWithMoreComentario(int qtdResult);
    public int countLocalizacaoByDescricaoInLarLng(String categoria, Bounds bounds);
    public int countByAprovado(boolean flgAtivo);
    public List<Localizacao> findSimpleLocalizacaoByAprovado(int qtdResult, int page, boolean flgAtivo);
    public List<Localizacao> findLocalizacaoByLatLngOrderByNome(double latitude, double longitude);
    public List<LocalizacaoGroupQuery> findGroupLocalizacaoByLatLng();
 	
}
