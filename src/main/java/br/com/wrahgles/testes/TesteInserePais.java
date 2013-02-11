package br.com.wrahgles.testes;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.wrahgles.dao.EstadoHibernateDAO;
import br.com.wrahgles.model.Estado;

public class TesteInserePais {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        Session session = null;
		EstadoHibernateDAO dao = new EstadoHibernateDAO(session);
		Estado estado = new Estado();
        List<Estado> estadoList = new ArrayList<Estado>();
	
         Estado estado2 = new Estado();
         estadoList = dao.findAllOrderNome();
         
         System.out.println(estado.getNome());
         System.out.println(estado.getSigla());

	}

}
