package br.com.wrahgles.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wrahgles.model.Convite;
@Component
public class ConviteHibernateDAO extends GenericoHibernateDAO<Convite, Long> implements ConviteDAO{

	private final Session session;
	
	public ConviteHibernateDAO(Session session) {
		super(session);
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Convite> findByFlgEnvioEmail(boolean flgEnvioEmail) {
	  Criteria crit = session.createCriteria(Convite.class);
	  crit.add(Restrictions.eq("flgEnvioEmail", flgEnvioEmail));
	  crit.addOrder(Order.asc("dtConvite"));
	  return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Convite> findByEmailAndFlgEnvioEmail(String email, boolean flgEnvioEmail) {
	   Criteria crit = session.createCriteria(Convite.class);
	   crit.add(Restrictions.eq("flgEnvioEmail", flgEnvioEmail));
	   crit.add(Restrictions.eq("email", email));
	   crit.addOrder(Order.desc("dtConvite"));
		return crit.list();
	}

	@Override
	public Convite findByIdUsuarioAndEmail(Long idUsuario, String email) {
		Criteria crit = session.createCriteria(Convite.class);
		crit.add(Restrictions.eq("usuario.id", idUsuario));
		crit.add(Restrictions.eq("email", email));
		
		Object obj = crit.uniqueResult();
		if(obj == null)
			return null;
		return (Convite)obj;
	}
	
	public Convite findById(Long id){
		Criteria crit = session.createCriteria(Convite.class);
		crit.add(Restrictions.eq("usuario.id", id));
		return (Convite) crit.uniqueResult();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Convite> findByEmail(String email) {
	Criteria crit = session.createCriteria(Convite.class);
	crit.add(Restrictions.eq("email", email));
		return crit.list();
	}

}
