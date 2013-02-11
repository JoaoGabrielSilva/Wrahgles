package br.com.wrahgles.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class VisitaLocalizacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private UsuarioLocalizacaoPK id;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtVisita;

	public UsuarioLocalizacaoPK getId() {
		return id;
	}

	public void setId(UsuarioLocalizacaoPK id) {
		this.id = id;
	}

	public Date getDtVisita() {
		return dtVisita;
	}

	public void setDtVisita(Date dtVisita) {
		this.dtVisita = dtVisita;
	}
	
	
	
	

}
