package br.com.wrahgles.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wrahgles.bean.query.ComentarioQuery;
import br.com.wrahgles.model.Comentario;

@Component
public class ComentarioHibernateDAO extends GenericoHibernateDAO<Comentario, Long> implements ComentarioDAO{

	private final Session session;
	private final LocalizacaoFotoDAO daoFotoLocal;
	
	public ComentarioHibernateDAO(Session session, LocalizacaoFotoDAO daoFotoLocal ){
		super(session);
		this.session = session;
		this.daoFotoLocal = daoFotoLocal;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comentario> findComentarioByLocalizacaoId(Long idLocalizacao) {
		Criteria  crit = session.createCriteria(Comentario.class);
		crit.add(Restrictions.eq("localizacao.id", idLocalizacao));// faz a restrição da pesquisa
		crit.addOrder(Order.desc("dataComentario"));//tras a lista ordenada por data de comentario decrescente

		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComentarioQuery> findComentarioByUsuarioId(Long idUsuario) {
		Criteria crit = session.createCriteria(Comentario.class);
		crit.add(Restrictions.eq("usuario.id", idUsuario));
		crit.addOrder(Order.desc("dataComentario"));
		
		List<ComentarioQuery> lista = new ArrayList<ComentarioQuery>();
		List<Comentario> list = crit.list();
		for(Comentario c: list){
			ComentarioQuery com = new ComentarioQuery();
			com.setComentario(c);
			com.setFoto(daoFotoLocal.findByIdLocalPrimeiraFoto(c.getLocalizacao().getId()));
			lista.add(com);
		}
		
		
		return null;
	}

	@Override
	public int countComentarioByUsuarioId(Long idUsuario) {
       Criteria crit = session.createCriteria(Comentario.class);
       crit.setProjection(Projections.rowCount());
       crit.add(Restrictions.eq("usuario.id", idUsuario));
       
       Object obj = crit.uniqueResult();
       if(obj == null)
    	   return 0;
		return (Integer) obj;
	}

	@Override
	public Comentario findComentarioByUsuarioAndLocalizacao(Long idUsuario,
			Long idLocalizacao) {
		Criteria crit = session.createCriteria(Comentario.class);
		crit.add(Restrictions.eq("localizacao.id", idLocalizacao));
		crit.add(Restrictions.eq("usuario.id", idUsuario));
		Object obj =crit.uniqueResult();
		if(obj == null)
			return null;
		return (Comentario) obj;
	}

	@Override
	public int countComentarioByLocalizacao(Long idLocalizacao) {
       Criteria crit = session.createCriteria(Comentario.class);
       crit.setProjection(Projections.rowCount());
       crit.add(Restrictions.eq("localizacao.id", idLocalizacao));
       
       Object obj = crit.uniqueResult();
       if(obj == null)
    	   return 0;
		return (Integer) obj;
	}

	@Override
	public int sumNotasComentariosByLocalizacao(Long idLocalizacao) {
        Criteria crit = session.createCriteria(Comentario.class);
        crit.setProjection(Projections.sum("nota"));
        crit.add(Restrictions.eq("localizacao.id", idLocalizacao));
        
        Object obj = crit.uniqueResult();
        if(obj == null)
        	return 0;
      
		return (Integer) obj;
	}

	@Override
	public int countByAprovado(boolean flgAtivo) {
		Criteria crit = session.createCriteria(Comentario.class);
		crit.setProjection(Projections.rowCount());
		crit.add(Restrictions.eq("flgAtivo", flgAtivo));
		
		Object obj = crit.uniqueResult();
		if(obj == null)
			return 0;
		
		return (Integer) obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comentario> findForPaginationByAprovado(int qtdResult, int page, boolean flgAtivo) {
		Criteria crit = session.createCriteria(Comentario.class);
		crit.add(Restrictions.eq("flgAtivo", flgAtivo));
		crit.setMaxResults(qtdResult);
		crit.setFirstResult((qtdResult * (page + 1)) - qtdResult);
		
		return crit.list();
	}

	@Override
	public Comentario findUltimoComentarioByIdUsuario(Long idUsuario) {
       Criteria crit = session.createCriteria(Comentario.class);
       crit.add(Restrictions.eq("usuario.id", idUsuario));
       crit.addOrder(Order.desc("dataComentario"));
       crit.setMaxResults(1);
       
       Object obj = crit.uniqueResult();
       if(obj == null)
    	   return null;
		return (Comentario) obj;
	}

}
