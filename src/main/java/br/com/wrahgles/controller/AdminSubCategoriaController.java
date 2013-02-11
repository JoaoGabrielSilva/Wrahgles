package br.com.wrahgles.controller;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.wrahgles.annotation.ReturnPageAfterLogin;
import br.com.wrahgles.dao.SubCategoriaDAO;
import br.com.wrahgles.model.SubCategoria;
import br.com.wrahgles.properties.AdminProperties;
import static br.com.caelum.vraptor.view.Results.json;

@LoggedIn
@Resource
public class AdminSubCategoriaController {
	
	private final Result result;
	private final Validator validator;
	private final SubCategoriaDAO dao;
	private final AdminProperties properties;
	
	public AdminSubCategoriaController(Result result, Validator validator,
			SubCategoriaDAO dao, AdminProperties properties) {
		super();
		this.result = result;
		this.validator = validator;
		this.dao = dao;
		this.properties = properties;
		result.include("menu", "subcategoria");
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/subcategoria/form")
	public void form(){}
	
	@ReturnPageAfterLogin
	@Path("/admin/subcategoria/list")
	public void list(){
		result.include("lista", dao.findAll());
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/subcategoria/enviar")
	public void enviar(SubCategoria subcategoria){
		validaForm(subcategoria);
		
		if(subcategoria.getId() == null){
			dao.saveOrUpdate(subcategoria);
			result.include("mensagem", properties.getCadastro_subcategoria());
		}else {
			SubCategoria c = dao.findById(subcategoria.getId());
			c.setNome(subcategoria.getNome());
			dao.saveOrUpdate(c);
			result.include("mensagem", properties.getAtualizado_subcatoria());
  	   }
		result.use(Results.logic()).redirectTo(AdminSubCategoriaController.class).list();
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/subcategoria/alterar/{id}")
	public void alterar(Long id) throws Exception {
		result.include("subcategoria", dao.findById(id));
		result.use(Results.logic()).redirectTo(AdminSubCategoriaController.class).form();
		
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/subcategoria/excluir/{id}")
	public void excluir(Long id){
		dao.delete(dao.findById(id));
		result.include("mensagem", properties.getExcluir_subcategoria());
		result.use(Results.logic()).redirectTo(AdminSubCategoriaController.class).list();
		
	}
	
	@Path("/admin/subcategoria/listJSON")
	public void listJSON(String q){
		result.use(json()).from(dao.findByNomeILike(q)).exclude("id").serialize();
	}
	
	private void validaForm(SubCategoria subcategoria){
		if(subcategoria.getNome().trim().isEmpty()){
			validator.add(new ValidationMessage(properties.getCampo_nome_obrigatorio(), "error"));
		}else {
			SubCategoria c = dao.findSubCategoriaByNome(subcategoria);
			
			if(c != null && subcategoria.getId() == null)
				validator.add(new ValidationMessage(properties.getSubcateoria_sistema(), "error"));
			
			if(subcategoria.getId() != null && c != null && subcategoria.getId().longValue() != c.getId().longValue())
				validator.add(new ValidationMessage(properties.getSubcateoria_sistema(),"error"));
		}
		validator.onErrorUse(Results.page()).of(AdminSubCategoriaController.class).form();
	}
	
	

}
