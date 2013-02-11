package br.com.wrahgles.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wrahgles.bean.query.LocalizacaoGroupQuery;
import br.com.wrahgles.bean.query.LocalizacaoQuery;
import br.com.wrahgles.model.Bounds;
import br.com.wrahgles.model.Comentario;
import br.com.wrahgles.model.Localizacao;
@Component
public class LocalizacaoHibernateDAO extends GenericoHibernateDAO<Localizacao, Long> implements LocalizacaoDAO{

	private final Session session;
	private final ComentarioDAO daoComentario;
	
	public LocalizacaoHibernateDAO(Session session, ComentarioDAO daoComentario) {
		super(session);
		this.session = session;
		this.daoComentario = daoComentario;
	}

	@Override
	public List<LocalizacaoQuery> findLocalizacaoByDescricaoInLatLng(
			String categoria, Bounds bounds, int qtdResult, int page) {
		Criteria crit = session.createCriteria(Localizacao.class);
		Criterion cOr = Restrictions.or(
				Restrictions.ilike("nome", "%" + categoria.trim() + "%"),
				Restrictions.ilike("descricao", "%" + categoria.trim() + "%"));
		Criterion cLat = Restrictions.between("latitude", bounds.getSul().getLatitude(), bounds.getNorte().getLatitude());
		Criterion cLng = Restrictions.between("longitude", bounds.getSul().getLongitude(), bounds.getNorte().getLongitude());
		
		crit.add(cOr);
		crit.add(cLat);
		crit.add(cLng);
		
		crit.setMaxResults(qtdResult);
		crit.setFirstResult((qtdResult * (page + 1 )) - qtdResult);
		
		List<LocalizacaoQuery> lista = new  ArrayList<LocalizacaoQuery>();
		@SuppressWarnings("unchecked")
		List<Localizacao> listLocal = crit.list();
		
		for(Localizacao local : listLocal){
			LocalizacaoQuery localQ = new LocalizacaoQuery();
			localQ.setLocal(local);
			localQ.setQtdComentarios(daoComentario.countComentarioByLocalizacao(local.getId()));
			localQ.setSomaNota(daoComentario.sumNotasComentariosByLocalizacao(local.getId()));
			localQ.setNota(Math.round(localQ.getSomaNota() / localQ.getQtdComentarios()));
			lista.add(localQ);
			
			
		}
		
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalizacaoQuery> findLocalizacaoWithMoreComentario(int qtdResult) {
		Criteria crit = session.createCriteria(Comentario.class, "c");
		crit.setProjection(Projections.projectionList()
				.add(Projections.alias(Projections.count("c.localizacao"), "qtdComentarios"))
				.add(Projections.alias(Projections.groupProperty("c.localizacao"), "local"))
				.add(Projections.alias(Projections.sum("c.nota"), "somaNota")));
		crit.addOrder(Order.desc("qtdComentarios"));
		crit.addOrder(Order.desc("c.dataComentario"));
		crit.setMaxResults(qtdResult);
		crit.setResultTransformer(Transformers.aliasToBean(LocalizacaoQuery.class));
		
		return crit.list();
	}

	@Override
	public int countLocalizacaoByDescricaoInLarLng(String categoria, Bounds bounds) {
		Criteria crit = session.createCriteria(Localizacao.class);
		Criterion cOr = Restrictions.or(
				Restrictions.ilike("nome", "%" + categoria.trim() + "%"),
				Restrictions.ilike("descricao", "%" + categoria.trim() + "%"));
		Criterion cLat = Restrictions.between("latitude", bounds.getSul().getLatitude(), bounds.getNorte().getLatitude());
		Criterion cLng = Restrictions.between("longitude", bounds.getSul().getLongitude(), bounds.getNorte().getLongitude());
		
		
		crit.setProjection(Projections.rowCount());
		crit.add(cOr);
		crit.add(cLat);
		crit.add(cLng);
		
		Object obj = crit.uniqueResult();
		if(obj == null)
			return 0;
		
		return (Integer) obj;
	}

	@Override
	public int countByAprovado(boolean flgAtivo) {
		Criteria crit = session.createCriteria(Localizacao.class);
		crit.setProjection(Projections.rowCount());
		crit.add(Restrictions.eq("flgAtivo", flgAtivo));
		
		Object obj = crit.uniqueResult();
		if(obj == null)
			return 0;
		
		return (Integer) obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Localizacao> findSimpleLocalizacaoByAprovado(int qtdResult,
			int page, boolean flgAtivo) {
	    Criteria crit = session.createCriteria(Localizacao.class);
	    crit.add(Restrictions.eq("flgAtivo", flgAtivo));
	    crit.setMaxResults(qtdResult);
	    crit.setFirstResult((qtdResult * (page + 1) - qtdResult));
	    
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Localizacao> findLocalizacaoByLatLngOrderByNome(
			double latitude, double longitude) {
		Criteria crit = session.createCriteria(Localizacao.class);
		crit.add(Restrictions.eq("latitude", latitude));
		crit.add(Restrictions.eq("longitude", longitude));
		crit.addOrder(Order.asc("nome"));
		
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalizacaoGroupQuery> findGroupLocalizacaoByLatLng() {
	    Criteria crit = session.createCriteria(Localizacao.class, "l");
	    crit.setProjection(Projections.projectionList()
	    		.add(Projections.alias(Projections.count("l.latitude"), "qtdLocal"))
	    		.add(Projections.alias(Projections.groupProperty("l.latitude"), "latitude"))
	    		.add(Projections.alias(Projections.groupProperty("l.longitude"), "longitude")));

	    crit.addOrder(Order.desc("qtdLocal"));
	    crit.setResultTransformer(Transformers.aliasToBean(LocalizacaoGroupQuery.class));
	    
	    return crit.list();
	}

}
