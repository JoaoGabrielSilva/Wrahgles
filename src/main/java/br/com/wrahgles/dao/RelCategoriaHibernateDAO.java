package br.com.wrahgles.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wrahgles.model.RelCategoria;

@Component
public class RelCategoriaHibernateDAO extends GenericoHibernateDAO<RelCategoria, Long> implements RelCategoriaDAO {

	private final Session session;
	
	
	public RelCategoriaHibernateDAO(Session session) {
		super(session);
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RelCategoria> findByIdCategoria(Long id) {
	     Criteria crit = session.createCriteria(RelCategoria.class);
	     crit.add(Restrictions.eq("categoria.id", id));
	     crit.createAlias("subCategoria", "sub").addOrder(Order.asc("sub.nome"));
		return crit.list();
	}

	@Override
	public RelCategoria findByIdCategoriaAndIdSubCategoria(Long idCategoria,
			Long idSubcategoria) {
		Criteria crit = session.createCriteria(RelCategoria.class);
		crit.add(Restrictions.eq("categoria.id", idCategoria));
		crit.add(Restrictions.eq("subCategoria.id", idSubcategoria));
		
		Object obj = crit.uniqueResult();
		if(obj == null)
			return null;
		
		return (RelCategoria) obj;
	}

}
