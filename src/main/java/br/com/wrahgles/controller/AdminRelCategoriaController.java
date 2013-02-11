package br.com.wrahgles.controller;

import java.util.List;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.wrahgles.annotation.ReturnPageAfterLogin;
import br.com.wrahgles.dao.CategoriaDAO;
import br.com.wrahgles.dao.RelCategoriaDAO;
import br.com.wrahgles.dao.SubCategoriaDAO;
import br.com.wrahgles.model.RelCategoria;
import br.com.wrahgles.properties.AdminProperties;

@LoggedIn
@Resource
public class AdminRelCategoriaController {
	
	private final Result result;
	private final RelCategoriaDAO dao;
	private final CategoriaDAO daoCategoria;
	private final SubCategoriaDAO daoSubCategoria;
	private final Validator validator;
	private final AdminProperties properties;
	
	
	public AdminRelCategoriaController(Result result, RelCategoriaDAO dao,
			CategoriaDAO daoCategoria, SubCategoriaDAO daoSubCategoria,
			Validator validator, AdminProperties properties) {
		super();
		this.result = result;
		this.dao = dao;
		this.daoCategoria = daoCategoria;
		this.daoSubCategoria = daoSubCategoria;
		this.validator = validator;
		this.properties = properties;
		this.result.include("menu", "categoria");
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/relCategoria/form/{idCategoria}")
	public void form(Long idCategoria){
		RelCategoria rel = new RelCategoria();
		rel.setCategoria(daoCategoria.findById(idCategoria));
		result.include("relCategoria", rel);
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/relCategoria/list/{idCategoria}")
	public List<RelCategoria> list(Long idCategoria){
		result.include("categoria", daoCategoria.findById(idCategoria));
		return dao.findByIdCategoria(idCategoria);
		
	}
	
	@Path("/admin/relcategoria/salvar/{idCategoria}")
	public void salvar(RelCategoria relCategoria, Long idCategoria){
		relCategoria.setCategoria(daoCategoria.findById(idCategoria));
		validaForm(relCategoria);
		relCategoria.setSubCategoria(daoSubCategoria.findSubCategoriaByNome(relCategoria.getSubCategoria()));
		dao.saveOrUpdate(relCategoria);
		result.redirectTo(this).list(idCategoria);
		
	}
	
	private void validaForm(RelCategoria relCategoria){
		if(relCategoria.getSubCategoria().getNome().trim().isEmpty())
			validator.add(new ValidationMessage(properties.getCampo_subcategoria_obrigatorio(), "error"));
		else {
			relCategoria.setSubCategoria(daoSubCategoria.findSubCategoriaByNome(relCategoria.getSubCategoria()));
			if(relCategoria.getSubCategoria() == null)
				validator.add(new ValidationMessage(properties.getSubcategoria_nao_encontrada(), "error"));
			else if(dao.findByIdCategoriaAndIdSubCategoria(relCategoria.getCategoria().getId(), relCategoria.getSubCategoria().getId()) != null)
				validator.add(new ValidationMessage(properties.getSubcategoria_relacionada(), "error"));
		}
		
		if(relCategoria.getDescricao().trim().isEmpty())
			validator.add(new ValidationMessage(properties.getCampo_descricao_obrigatorio(), "error"));
		
		validator.onErrorUse(Results.page()).of(AdminRelCategoriaController.class).form(relCategoria.getCategoria().getId());
	}

}
