package br.com.wrahgles.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wrahgles.model.UsuarioAmigo;
import br.com.wrahgles.model.UsuarioAmigoPK;

@Component
public class UsuarioAmigoHibernateDAO implements UsuarioAmigoDAO{

	private final Session session;
	
	public UsuarioAmigoHibernateDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioAmigo> listAmigos(Long id, boolean flgConfirmacao) {
		Criteria crit = session.createCriteria(UsuarioAmigo.class);
		crit.add(Restrictions.eq("id.usuario.id", id));
		crit.add(Restrictions.eq("flgConfirmacao", flgConfirmacao));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioAmigo> listSolicitacoesPendentes(Long id) {
		Criteria crit = session.createCriteria(UsuarioAmigo.class);
		crit.add(Restrictions.eq("id.usuarioAmigo.id", id));
		crit.add(Restrictions.eq("flgConfirmacao", false));
		return crit.list();
	}

	@Override
	public int countSolicitacoesPendentes(Long id) {
		Criteria crit = session.createCriteria(UsuarioAmigo.class);
		crit.add(Restrictions.eq("id.usuarioAmigo.id", id));
		crit.add(Restrictions.eq("flgConfirmacao", false));
		
		Object obj = crit.uniqueResult();
		if(obj == null)
			return 0;
		
		return (Integer) obj;
	}

	@Override
	public List<UsuarioAmigo> listAmigos(Long id, boolean flgConfirma, int qtdResult) {
		Criteria crit = session.createCriteria(UsuarioAmigo.class);
		crit.setMaxResults(qtdResult);
		crit.add(Restrictions.eq("d.usuario.id", id));
		crit.add(Restrictions.eq("flgConfirmacao", flgConfirma));
		
		return null;
	}

	@Override
	public int countAmigos(Long id, boolean flgConfirma) {
		Criteria crit = session.createCriteria(UsuarioAmigo.class);
		crit.setProjection(Projections.rowCount());
		crit.add(Restrictions.eq("id.usuario.id", id));
		crit.add(Restrictions.eq("flgConfirmacao", flgConfirma));
		
		Object obj = crit.uniqueResult();
		if(obj == null)
			return 0;		
		
		return (Integer) obj;
	} 

	@Override
	public UsuarioAmigo findById(UsuarioAmigoPK id) {
		Criteria crit = session.createCriteria(UsuarioAmigo.class);
		crit.add(Restrictions.eq("id", id));
		
		Object obj = crit.uniqueResult();
		if(obj == null)
			return null;
		return (UsuarioAmigo) obj;
	}

	@Override
	public void saveOrUpdate(UsuarioAmigo usuarioAmigo) {
		session.saveOrUpdate(usuarioAmigo);
		
	}

	@Override
	public void save(UsuarioAmigo usuarioAmigo) {
		session.save(usuarioAmigo);
		
	}

	@Override
	public void update(UsuarioAmigo usuarioAmigo) {
		session.update(usuarioAmigo);
		
	}

	@Override
	public void delete(UsuarioAmigo usuarioAmigo) {
		session.delete(usuarioAmigo);
		
	}

	
	
}
