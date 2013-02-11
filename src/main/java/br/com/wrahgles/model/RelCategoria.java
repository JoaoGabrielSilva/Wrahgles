package br.com.wrahgles.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RelCategoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, length = 250)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="ID_CATEGORIA", nullable = false)
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="ID_SUB_CATEGORIA", nullable = false)
	private SubCategoria subCategoria;
	
	
	public RelCategoria() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public SubCategoria getSubCategoria() {
		return subCategoria;
	}


	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}
	
	
	
	
	
	
	
	

}
