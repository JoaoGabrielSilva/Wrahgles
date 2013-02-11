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
public class Comentario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, length = 1000)
	private String comentario;
	
	@Column(nullable = false)
	private int nota;
	 
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataComentario;
	
	@Column(nullable = false)
	private boolean flgAtivo;
	
	public Comentario() {}
	
	@ManyToOne
	@JoinColumn(name="ID_USUARIO",nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="ID_LOCALIZACAO", nullable = false)
	private Localizacao localizacao;
	
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Date getDataComentario() {
		return dataComentario;
	}

	public void setDataComentario(Date dataComentario) {
		this.dataComentario = dataComentario;
	}

	public boolean isFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
	}
	
	
	
}
