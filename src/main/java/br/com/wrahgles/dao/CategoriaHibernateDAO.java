package br.com.wrahgles.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wrahgles.model.Categoria;

@Component
public class CategoriaHibernateDAO extends GenericoHibernateDAO<Categoria,Long> implements CategoriaDAO {

	private final Session session;
	
	public CategoriaHibernateDAO(Session session){
		super(session);
		this.session = session;
	}
	
	
	
	@Override
	public Categoria findCategoriaByNome(Categoria categoria) {
		Query q = session.createQuery("from Categoria c where c.nome = :nome");
		q.setParameter("nome", categoria.getNome().trim());
		
		Object obj = q.uniqueResult();
		if(obj == null)
			return null;

		return (Categoria) obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> findAllOrderName() {
		return session.createCriteria(Categoria.class).addOrder(Order.asc("nome")).list();
	}

}
