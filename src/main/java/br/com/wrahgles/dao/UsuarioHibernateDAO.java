package br.com.wrahgles.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wrahgles.bean.query.UsuarioQuery;
import br.com.wrahgles.model.Comentario;
import br.com.wrahgles.model.Usuario;

@Component
public class UsuarioHibernateDAO extends GenericoHibernateDAO<Usuario, Long> implements UsuarioDAO {

	private final Session session;
	private final ComentarioDAO daoComentario;
	
	public UsuarioHibernateDAO(Session session, ComentarioDAO daoComentario) {
		super(session);
		this.session = session;
		this.daoComentario = daoComentario;
	}

	@Override
	public Usuario findByEmailAndSenha(Usuario usuario) {
		Query q = session.createQuery("from Usuario u where u.email = :email and u.senha = :senha");
		q.setParameter("email", usuario.getEmail().trim());
		q.setParameter("senha", usuario.getSenha().trim());
		
		Object obj = q.uniqueResult();
		if(obj == null)
			return null;
		return (Usuario) obj;
	}

	@Override
	public Usuario findByEmail(Usuario usuario) {
		Query q = session.createQuery("from Usuario u where u.email = :email");
		q.setParameter("email", usuario.getEmail().trim());
		
		Object obj = q.uniqueResult();
		if(obj == null)
			return null;
		
		return (Usuario) obj;
	}

	@Override
	public Usuario findByEmail(String email) {
		Query q = session.createQuery("from Usuario u where u.email = :email");
		q.setParameter("email", email.trim());
		
		Object obj = q.uniqueResult();
		if(obj == null)
			return null;
		
		return (Usuario) obj;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findByFlgInfoWrahgles(boolean valor) {
		Criteria crit = session.createCriteria(Usuario.class);
		crit.add(Restrictions.eq("flgInfoWrahgles", valor));
		
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findByNomeOrEmailOrderDtUltimoAcesso(String str, int qtdResult, int page) {
		Criteria crit = session.createCriteria(Usuario.class);
		Criterion cOr = Restrictions.or(
				Restrictions.ilike("nome", "%" + str.trim() + "%"),
				Restrictions.ilike("email","%" + str.trim() + "%"));
		
		crit.add(cOr);
		crit.setMaxResults(qtdResult);
		crit.setFirstResult((qtdResult * (page + 1)) - qtdResult);
		crit.addOrder(Order.desc("dtUltimoAcesso"));
		
		return crit.list();
	}

	@Override
	public int countByNomeOrEmail(String str) {
		Criteria crit = session.createCriteria(Usuario.class);
		Criterion cOr = Restrictions.or(
				Restrictions.ilike("nome","%" + str.trim() + "%"),
				Restrictions.ilike("email", "%" + str.trim() + "%"));
		crit.setProjection(Projections.rowCount());
		crit.add(cOr);
		
		Object obj = crit.uniqueResult();
		if(obj == null )
			return 0;
		return (Integer)obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findAllOrderDtCadastro(int qtdResult, int page) {
		Criteria crit = session.createCriteria(Usuario.class);
		crit.setMaxResults(qtdResult);
		crit.setFirstResult(qtdResult * (page + 1) - qtdResult);
		crit.addOrder(Order.desc("dtCadastro"));
		
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioQuery> findWithMoreComentario(int qtdResult) {
		Criteria crit = session.createCriteria(Comentario.class,"c");
		crit.setProjection(Projections.projectionList()
		.add(Projections.alias(Projections.count("c.usuario"), "qtdComentarios"))
		.add(Projections.alias(Projections.groupProperty("c.usuario"), "usuario")));
		crit.addOrder(Order.desc("qtdComentarios"));
		crit.setMaxResults(qtdResult);
		crit.setResultTransformer(Transformers.aliasToBean(UsuarioQuery.class));
		
		List<UsuarioQuery> lista = crit.list();
		for(UsuarioQuery u: lista)
			u.setUltimoComentario(daoComentario.findUltimoComentarioByIdUsuario(u.getUsuario().getId()));
		
		return null;
	}

}
