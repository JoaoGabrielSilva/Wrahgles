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
public class Denuncia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, length = 1000)
	private String comentario;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtDenuncia;
	
	@Column(nullable = false)
	private String feedback;
	
	@ManyToOne
	@JoinColumn(name="ID_USUARIO_REVISAO", nullable = false)
	private Usuario usuarioRevisao;
	
	@ManyToOne
	@JoinColumn(name="ID_USUARIO" , nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="ID_LOCALIZACAO" , nullable = false)
	private Localizacao localizacao;
	
	public Denuncia() {}

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

	public Date getDtDenuncia() {
		return dtDenuncia;
	}

	public void setDtDenuncia(Date dtDenuncia) {
		this.dtDenuncia = dtDenuncia;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Usuario getUsuarioRevisao() {
		return usuarioRevisao;
	}

	public void setUsuarioRevisao(Usuario usuarioRevisao) {
		this.usuarioRevisao = usuarioRevisao;
	}

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
	
	
	

}
