package br.com.wrahgles.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wrahgles.model.SubCategoria;

@Component
public class SubCategoriaHibernateDAO extends GenericoHibernateDAO<SubCategoria, Long> implements SubCategoriaDAO{

	private final Session session;
	
	
	public SubCategoriaHibernateDAO(Session session) {
		super(session);
		this.session = session;
	}

	@Override
	public SubCategoria findSubCategoriaByNome(SubCategoria subCategoria) {
	 Query q = session.createQuery("from SubCategoria c where c.nome = :nome");
	  q.setParameter("nome", subCategoria.getNome().trim());
		
	   Object obj = q.uniqueResult();
	   if(obj == null)
		   return null;
	  
		return (SubCategoria) obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubCategoria> findByNomeILike(String nome) {
		return session.createCriteria(SubCategoria.class).add(Restrictions.ilike("nome", nome.trim(), MatchMode.ANYWHERE)).list();
	}

}
