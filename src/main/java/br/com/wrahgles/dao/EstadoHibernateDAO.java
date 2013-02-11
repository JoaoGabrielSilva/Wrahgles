package br.com.wrahgles.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wrahgles.model.Estado;

@Component
public class EstadoHibernateDAO extends GenericoHibernateDAO<Estado, Long> implements EstadoDAO {

	
	private final Session session;
	
	public EstadoHibernateDAO(Session session) {
		super(session);
		this.session = session;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Estado> findAllOrderNome() {
	  return session.createCriteria(Estado.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public Estado findBySigla(String sigla) {
         Criteria crit = session.createCriteria(Estado.class);
         crit.add(Restrictions.eq("sigla", sigla));
         
         Object obj = crit.uniqueResult();
         if(obj == null)
        	 return null;
         
		return (Estado) obj;
	}

}
