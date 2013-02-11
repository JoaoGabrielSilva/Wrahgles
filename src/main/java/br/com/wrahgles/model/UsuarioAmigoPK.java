package br.com.wrahgles.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioAmigoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="ID_USUARIO_AMIGO", nullable = false)
	private Usuario usuarioAmigo;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioAmigo() {
		return usuarioAmigo;
	}

	public void setUsuarioAmigo(Usuario usuarioAmigo) {
		this.usuarioAmigo = usuarioAmigo;
	}
	
	
	
	

}
