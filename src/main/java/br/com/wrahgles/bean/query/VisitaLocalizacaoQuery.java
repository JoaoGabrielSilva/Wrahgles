package br.com.wrahgles.bean.query;

import br.com.wrahgles.model.Comentario;
import br.com.wrahgles.model.VisitaLocalizacao;

public class VisitaLocalizacaoQuery {
	
	private VisitaLocalizacao visita;
	private Comentario ultimoComentario;
	
	public VisitaLocalizacao getVisita() {
		return visita;
	}
	public void setVisita(VisitaLocalizacao visita) {
		this.visita = visita;
	}
	public Comentario getUltimoComentario() {
		return ultimoComentario;
	}
	public void setUltimoComentario(Comentario ultimoComentario) {
		this.ultimoComentario = ultimoComentario;
	}
	
	

}
