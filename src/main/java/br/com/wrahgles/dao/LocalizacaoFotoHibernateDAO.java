package br.com.wrahgles.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wrahgles.model.LocalizacaoFoto;

@Component
public class LocalizacaoFotoHibernateDAO extends GenericoHibernateDAO<LocalizacaoFoto, Long> implements LocalizacaoFotoDAO {

	private final Session session;
	
	public LocalizacaoFotoHibernateDAO(Session session) {
		super(session);
		this.session = session;
	}

	@Override
	public boolean existeNomeImagem(Long idLocalizacao, String imagem) {
		Criteria crit = session.createCriteria(LocalizacaoFoto.class);
		crit.add(Restrictions.eq("id", idLocalizacao));
		crit.add(Restrictions.eq("imagem", imagem));
		
		if(crit.uniqueResult() == null)
			return false;
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalizacaoFoto> findByAprovado(int qtdResult, int page, boolean fllgAtivo) {
		Criteria crit = session.createCriteria(LocalizacaoFoto.class);
		crit.add(Restrictions.eq("flgAtivo", fllgAtivo));
		crit.setMaxResults(qtdResult);
		crit.setFirstResult((qtdResult * (page + 1) - qtdResult));
		return crit.list();
	}

	@Override
	public int countByAprovado(boolean flgAtivo) {
		Criteria crit = session.createCriteria(LocalizacaoFotoDAO.class);
		crit.setProjection(Projections.rowCount());
		crit.add(Restrictions.eq("flgAtivo", flgAtivo));
		
		Object obj = crit.uniqueResult();
		if(obj == null)
			return 0;
		
		return (Integer) obj;
	}

	@Override
	public LocalizacaoFoto findByIdLocalPrimeiraFoto(Long idLocal) {
		Criteria crit = session.createCriteria(LocalizacaoFoto.class);
		crit.add(Restrictions.eq("localizacao.id", idLocal));
		crit.add(Restrictions.eq("flgAtivo", true));
		crit.setMaxResults(1);
		crit.addOrder(Order.asc("data"));
		
		Object obj = crit.uniqueResult();
		if(obj == null)
			return null;
		
		return (LocalizacaoFoto) obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalizacaoFoto> findByIdUsuario(Long idUsuario, int qtdResult) {
		Criteria crit = session.createCriteria(LocalizacaoFoto.class);
		crit.add(Restrictions.eq("usuario.id", idUsuario));
		crit.add(Restrictions.eq("flgAtivo", true));
		crit.setMaxResults(qtdResult);
		crit.addOrder(Order.desc("data"));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalizacaoFoto> findByIdLocalizacao(Long idLocalizacao) {
		Criteria crit = session.createCriteria(LocalizacaoFoto.class);
		crit.add(Restrictions.eq("localizacao.id", idLocalizacao));
		crit.add(Restrictions.eq("flgAtivo", true));
		crit.addOrder(Order.asc("data"));
		return crit.list();
	}

	@Override
	public int countByIdUsuario(Long idUsuario, boolean flgAtivo) {
		Criteria crit = session.createCriteria(LocalizacaoFoto.class);
		crit.setProjection(Projections.rowCount());
		crit.add(Restrictions.eq("usuario.id", idUsuario));
		crit.add(Restrictions.eq("flgAtivo", flgAtivo));
		
		Object obj = crit.uniqueResult();
		if(obj == null)
			return 0;
		
		return (Integer) obj;
	}

	@Override
	public int countByIdLocalizacao(Long idLocalizacao, boolean flgAtivo) {
	   Criteria crit = session.createCriteria(LocalizacaoFoto.class);
	   crit.setProjection(Projections.rowCount());
	   crit.add(Restrictions.eq("localizacao.id", idLocalizacao));
	   crit.add(Restrictions.eq("flgAtivo", flgAtivo));
	   
	   Object obj = crit.uniqueResult();
	   if(obj == null)
		   return 0; 
	   
		return (Integer) obj;
	}

}
