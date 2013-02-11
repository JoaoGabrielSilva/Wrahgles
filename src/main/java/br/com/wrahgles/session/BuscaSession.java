package br.com.wrahgles.session;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class BuscaSession implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String categoria;
	private String categoriaRandon;
	
	public BuscaSession(){}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCategoriaRandon() {
		return categoriaRandon;
	}

	public void setCategoriaRandon(String categoriaRandon) {
		this.categoriaRandon = categoriaRandon;
	}
	
	
	
	
	
	

}
