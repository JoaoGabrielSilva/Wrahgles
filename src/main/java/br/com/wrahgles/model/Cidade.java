package br.com.wrahgles.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cidade implements Serializable {

	private static final long serialVersionUID =1l;
	
	@Id
	@GeneratedValue
    private Long id;
	
	@Column(nullable = false, length = 150)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "ID_ESTADO", nullable = false)
	private Estado estado;

	public Cidade() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
	
	
}
