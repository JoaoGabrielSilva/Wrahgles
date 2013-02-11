package br.com.wrahgles.bean.query;

import br.com.wrahgles.model.Comentario;
import br.com.wrahgles.model.Usuario;

public class UsuarioQuery {
	
	private Usuario usuario;
	private Comentario ultimoComentario;
	private long qtdComentario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Comentario getUltimoComentario() {
		return ultimoComentario;
	}
	public void setUltimoComentario(Comentario ultimoComentario) {
		this.ultimoComentario = ultimoComentario;
	}
	public long getQtdComentario() {
		return qtdComentario;
	}
	public void setQtdComentario(long qtdComentario) {
		this.qtdComentario = qtdComentario;
	}
	

}
