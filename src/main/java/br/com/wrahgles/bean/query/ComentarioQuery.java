package br.com.wrahgles.bean.query;

import br.com.wrahgles.model.Comentario;
import br.com.wrahgles.model.LocalizacaoFoto;

public class ComentarioQuery {
	
	private Comentario comentario;
	private LocalizacaoFoto foto;
	
	public Comentario getComentario() {
		return comentario;
	}
	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}
	public LocalizacaoFoto getFoto() {
		return foto;
	}
	public void setFoto(LocalizacaoFoto foto) {
		this.foto = foto;
	}
	
	

}
