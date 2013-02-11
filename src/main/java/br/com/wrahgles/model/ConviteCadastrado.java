package br.com.wrahgles.model;

import java.io.Serializable;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ConviteCadastrado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private UsuarioAmigoPK id;

	public UsuarioAmigoPK getId() {
		return id;
	}

	public void setId(UsuarioAmigoPK id) {
		this.id = id;
	}
	
	
	

}
