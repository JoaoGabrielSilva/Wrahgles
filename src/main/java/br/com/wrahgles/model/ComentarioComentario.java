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
public class ComentarioComentario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, length = 500)
	private String texto;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtComentario;
	
	@ManyToOne
	@JoinColumn(name="ID_USUARIO", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name="ID_COMENTARIO", nullable = false)
	private Comentario comentario;
	
	public ComentarioComentario() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDtComentario() {
		return dtComentario;
	}

	public void setDtComentario(Date dtComentario) {
		this.dtComentario = dtComentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}
	
	
	
	

}
