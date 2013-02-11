package br.com.wrahgles.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wrahgles.model.Cidade;

@Component
public class CidadeHibernateDAO extends GenericoHibernateDAO<Cidade, Long> implements CidadeDAO {

	private final Session session;
	
	public CidadeHibernateDAO(Session session){
		super(session);
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> findByIdEstadoAndNomeILike(Long idEstado, String nome) {
		Criteria crit = session.createCriteria(Cidade.class);
		crit.add(Restrictions.eq("estado.id", idEstado));
		crit.add(Restrictions.ilike("nome", nome.trim(),MatchMode.ANYWHERE));
  
		return crit.list();
	}

	
	
}
