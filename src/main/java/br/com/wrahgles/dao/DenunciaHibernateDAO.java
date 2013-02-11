package br.com.wrahgles.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wrahgles.model.Denuncia;

@Component
public class DenunciaHibernateDAO extends GenericoHibernateDAO<Denuncia, Long> implements DenunciaDAO {

	private final Session session;
	
	public DenunciaHibernateDAO(Session session) {
		super(session);
		this.session = session;
	}

	@Override
	public Denuncia findDenunciaByUsuarioAndLocalizacao(Long idUsuario,
			Long idLocalizacao) {
		Criteria crit = session.createCriteria(Denuncia.class);
		crit.add(Restrictions.eq("localizacao.id", idLocalizacao));
		crit.add(Restrictions.eq("usuario.id", idUsuario));
		
		Object obj = crit.uniqueResult();
		if(obj == null)
			return null;
		
		return (Denuncia) obj;
	}

}
