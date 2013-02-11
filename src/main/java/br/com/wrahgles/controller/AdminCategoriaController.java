package br.com.wrahgles.controller;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.bronx.vraptor.restrictrex.annotation.Roles;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;

import br.com.wrahgles.annotation.ReturnPageAfterLogin;
import br.com.wrahgles.dao.CategoriaDAO;
import br.com.wrahgles.model.Categoria;
import br.com.wrahgles.properties.AdminProperties;

@LoggedIn
@Resource
public class AdminCategoriaController {
	
	private final Result result;
	private final Validator validator;
	private final CategoriaDAO dao;
	private final AdminProperties properties;
	
	public AdminCategoriaController(Result result, Validator validator, CategoriaDAO dao, AdminProperties properties){
		this.result = result;
		this.validator = validator;
		this.dao = dao;
		this.properties = properties;
		result.include("menu", "categoria");
	}
	
//	@Roles(roles = {"admin"})
	@ReturnPageAfterLogin
	@Path("admin/categoria/form")
	public void form(){			
	}
	
//	@Roles(roles = {"admin", "moderador"})
	@ReturnPageAfterLogin
	@Path("/admin/categoria/list")
	public void list(){
    	 result.include("lista", dao.findAll());
	}
//	
////	@Roles(roles = {"admin", "moderador"})
	@ReturnPageAfterLogin
	@Path("/admin/categoria/enviar")
	public void enviar(Categoria categoria){
		validaForm(categoria);
		if(categoria.getId() == null){
			dao.saveOrUpdate(categoria);
			result.include("mensagem", properties.getCategoria_cadastrada());
		}else {
			Categoria c = dao.findById(categoria.getId());
			c.setNome(categoria.getNome());
			dao.saveOrUpdate(c);
			result.include("mensagem", properties.getCategoria_atualizada());
		}
		result.use(Results.logic()).redirectTo(AdminCategoriaController.class).list();
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/categoria/alterar/{id}")
	public void alterar(Long id) throws Exception {
		result.include("categoria", dao.findById(id));
		result.use(Results.logic()).redirectTo(AdminCategoriaController.class).form();
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/categoria/excluir/{id}")
	public void excluir(Long id){
		dao.delete(dao.findById(id));
		result.include("mensagem", properties.getCategoria_excluida());
		result.use(Results.logic()).redirectTo(AdminCategoriaController.class).list();
	}
	
	

	private void validaForm(Categoria categoria) {
		/* Nome */
		if (categoria.getNome().trim().isEmpty()){
			validator.add(new ValidationMessage(properties.getCampo_nome_obrigatorio(), "error"));
		} else {
			Categoria c = dao.findCategoriaByNome(categoria);			
			
			if(c != null && categoria.getId() == null)
				validator.add(new ValidationMessage(properties.getCategoria_sistema(), "error"));
			
			if(categoria.getId() != null && c != null 
					&& categoria.getId().longValue() != c.getId().longValue())
				validator.add(new ValidationMessage(properties.getCategoria_sistema(), "error"));
		}	
		
		validator.onErrorUse(Results.page()).of(AdminCategoriaController.class).form();		
	}


}
