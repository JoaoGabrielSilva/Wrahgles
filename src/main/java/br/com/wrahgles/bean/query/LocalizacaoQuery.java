package br.com.wrahgles.bean.query;

import br.com.wrahgles.model.Localizacao;

public class LocalizacaoQuery {
	
	private double qtdComentarios;
	private double somaNota;
	private long nota;
	private Localizacao local;
	
	
	public double getQtdComentarios() {
		return qtdComentarios;
	}
	public void setQtdComentarios(double qtdComentarios) {
		this.qtdComentarios = qtdComentarios;
	}
	public double getSomaNota() {
		return somaNota;
	}
	public void setSomaNota(double somaNota) {
		this.somaNota = somaNota;
	}
	public long getNota() {
		return nota;
	}
	public void setNota(long nota) {
		this.nota = nota;
	}
	public Localizacao getLocal() {
		return local;
	}
	public void setLocal(Localizacao local) {
		this.local = local;
	}
	
	
	
	

}
