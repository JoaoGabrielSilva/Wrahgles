package br.com.wrahgles.model;

import java.io.Serializable;

public class Notificacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String mensagem;
	private boolean ativo;
	
	public Notificacao() {}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	

}
