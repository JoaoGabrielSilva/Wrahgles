package br.com.wrahgles.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Convite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtConvite;
	
	@Column(nullable = false)
	private boolean flgEnvioEmail;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", nullable = false)
	private Usuario usuario;
	
	public Convite(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDtConvite() {
		return dtConvite;
	}

	public void setDtConvite(Date dtConvite) {
		this.dtConvite = dtConvite;
	}

	public boolean isFlgEnvioEmail() {
		return flgEnvioEmail;
	}

	public void setFlgEnvioEmail(boolean flgEnvioEmail) {
		this.flgEnvioEmail = flgEnvioEmail;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	

}
