package br.com.wrahgles.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.wrahgles.roles.Perfil;

@Component
@SessionScoped
public class UsuarioLogado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Usuario usuario;
	private String uri;
	
	public UsuarioLogado(){}
	
	public void efetuaLogin(Usuario usuario){
		this.usuario = usuario;
	}
	
	public void efetuaLogoff(){
		this.usuario = null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public void removeUri(){
		this.uri = null;
	}
	
	public Perfil getPerfil(){
		Perfil perfil = new Perfil();
		if(this.getUsuario() != null){
			perfil.setLoggedIn(true);
			List<String> roles = new ArrayList<String>();
			roles.add(this.getUsuario().getRole());
			perfil.setRoles(roles);
		}
		return perfil;
	}
	
	

}
