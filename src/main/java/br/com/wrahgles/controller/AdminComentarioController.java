package br.com.wrahgles.controller;

import java.util.List;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.wrahgles.annotation.ReturnPageAfterLogin;
import br.com.wrahgles.dao.ComentarioDAO;
import br.com.wrahgles.model.Comentario;
import br.com.wrahgles.properties.AdminProperties;

@LoggedIn
@Resource
public class AdminComentarioController {
	private final Result result;
	private final ComentarioDAO dao;
	private final AdminProperties properties;
	
	public AdminComentarioController(Result result, ComentarioDAO dao,
			AdminProperties properties) {
		super();
		this.result = result;
		this.dao = dao;
		this.properties = properties;
	}
	@ReturnPageAfterLogin
    @Path("/admin/comentario/list")
	public void list(){
		result.include("submenu", "todas");
		result.include("qtdComentario", dao.count());
    }
	
	@ReturnPageAfterLogin
	@Path("/admin/comentario/listAprovado")
	public void listAprovado(){
		result.include("submenu", "aprovado");
		result.include("qtdComentario", dao.countByAprovado(true));
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/comentario/listPendente")
	public void listPendente(){
	   result.include("submenu", "pendente");
	   result.include("qtdComentarios", dao.countByAprovado(false));
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/comentario/excluir/{id}")
	public void excluir(Long id){
		dao.delete(dao.findById(id));
		result.include("mensagem", properties.getComentario_excluido());
		result.use(Results.logic()).redirectTo(AdminComentarioController.class).list();
		
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/comentario/aprovar/{id}")
	public void aprovar(Long id){
		Comentario com = dao.findById(id);
		com.setFlgAtivo(true);
		dao.saveOrUpdate(com);
		
		result.include("mensagem", properties.getComentario_aprovado());
		result.use(Results.logic()).redirectTo(AdminComentarioController.class).list();
		
	}
	
	@Path("/admin/comentario/pagination/{qtdResult}/{page}")
	public List<Comentario> pagination(int qtdResult, int page){
		return dao.findAll(qtdResult, page);
	}
	
	@Path("/admin/comentario/pagination/{qtdResult}/{page}/{status}")
	public List<Comentario> pagination(int qtdResult, int page, boolean status){
		return dao.findForPaginationByAprovado(qtdResult, page, status);
	}
	
	

}
