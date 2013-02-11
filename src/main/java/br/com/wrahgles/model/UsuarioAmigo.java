package br.com.wrahgles.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class UsuarioAmigo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private UsuarioAmigoPK id;
	
	private boolean flgConfirmacao;

	public UsuarioAmigoPK getId() {
		return id;
	}

	public void setId(UsuarioAmigoPK id) {
		this.id = id;
	}

	public boolean isFlgConfirmacao() {
		return flgConfirmacao;
	}

	public void setFlgConfirmacao(boolean flgConfirmacao) {
		this.flgConfirmacao = flgConfirmacao;
	}
	
	
	
	
	
	
	
}
