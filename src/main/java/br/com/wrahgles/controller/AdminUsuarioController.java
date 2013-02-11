package br.com.wrahgles.controller;

import java.util.List;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.wrahgles.annotation.ReturnPageAfterLogin;
import br.com.wrahgles.dao.UsuarioDAO;
import br.com.wrahgles.model.Usuario;

@LoggedIn
@Resource
public class AdminUsuarioController {
	
	private final Result result;
	private final UsuarioDAO dao;
	public AdminUsuarioController(Result result, UsuarioDAO dao) {
		this.result = result;
		this.dao = dao;
		result.include("menu","usuario");
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/usuario/list")
	public void list(){
		result.include("submenu", "todas");
		result.include("qtdUsuarios", dao.count());
	}
	
	@Path("/admin/usuario/pagination/{qtdResult}/{page}")
	public List<Usuario> pagination(int qtdResult, int page){
		return dao.findAll(qtdResult, page);
	}

}
