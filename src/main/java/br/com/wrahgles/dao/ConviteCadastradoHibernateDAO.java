package br.com.wrahgles.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wrahgles.model.ConviteCadastrado;

@Component
public class ConviteCadastradoHibernateDAO extends GenericoHibernateDAO<ConviteCadastrado, Long> implements ConviteCadastradoDAO {
	
	public ConviteCadastradoHibernateDAO(Session session) {
		super(session);
	}


}
