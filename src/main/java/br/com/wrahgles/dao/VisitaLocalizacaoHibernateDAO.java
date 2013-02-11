package br.com.wrahgles.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wrahgles.bean.query.VisitaLocalizacaoQuery;
import br.com.wrahgles.model.UsuarioLocalizacaoPK;
import br.com.wrahgles.model.VisitaLocalizacao;
@Component
public class VisitaLocalizacaoHibernateDAO implements VisitaLocalizacaoDAO {

	private final Session session;
	private final ComentarioDAO daoComentario;
	
	public VisitaLocalizacaoHibernateDAO(Session session, ComentarioDAO daoComentario) {
	  this.session = session;
	  this.daoComentario = daoComentario;
	}
	
	@Override
	public void saveOrUpdate(VisitaLocalizacao visitaLocalizacao) {
		session.clear();
		session.saveOrUpdate(visitaLocalizacao);
		
	}

	@Override
	public void save(VisitaLocalizacao visitaLocalizacao) {
        session.save(visitaLocalizacao);
	}

	@Override
	public void update(VisitaLocalizacao visitaLocalizacao) {
		session.update(visitaLocalizacao);
		
	}

	@Override
	public void delete(VisitaLocalizacao visitaLocalizacao) {
       session.delete(visitaLocalizacao);
		
	}

	@Override
	public VisitaLocalizacao findById(UsuarioLocalizacaoPK id) {
		Criteria crit = session.createCriteria(VisitaLocalizacao.class);
		crit.add(Restrictions.eq("id", id));
		
		Object obj = crit.uniqueResult();
		if(obj == null)
			return null;
		
		return (VisitaLocalizacao) obj;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<VisitaLocalizacaoQuery> findByIdLocalizacao(Long idLocalizacao, int qtdResult) {
         Criteria crit = session.createCriteria(VisitaLocalizacao.class);
         crit.setMaxResults(qtdResult);
         crit.add(Restrictions.eq("id.localizacao.id", idLocalizacao));
         crit.addOrder(Order.desc("dtVisita"));
         
         List<VisitaLocalizacaoQuery> newLista = new ArrayList<VisitaLocalizacaoQuery>();
   		 List<VisitaLocalizacao> lista = crit.list();
         for(VisitaLocalizacao visita : lista){
        	 VisitaLocalizacaoQuery q = new VisitaLocalizacaoQuery();
        	 q.setVisita(visita);
        	 q.setUltimoComentario(daoComentario.findUltimoComentarioByIdUsuario(visita.getId().getUsuario().getId()));
        	 newLista.add(q);
        	 
         }
         
		return newLista;
	}

	@Override
	public int countByIdLocalizacao(Long idLocalizacao) {
	   Criteria crit = session.createCriteria(VisitaLocalizacao.class);
	   crit.setProjection(Projections.rowCount());
	   crit.add(Restrictions.eq("id.localizacao.id", idLocalizacao));
	   
	   Object obj = crit.uniqueResult();
  	    if(obj == null)
		   return 0;
		return (Integer) obj;
	}

}
