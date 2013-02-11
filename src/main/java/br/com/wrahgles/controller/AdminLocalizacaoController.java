package br.com.wrahgles.controller;

import java.util.List;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.wrahgles.annotation.ReturnPageAfterLogin;
import br.com.wrahgles.dao.LocalizacaoDAO;
import br.com.wrahgles.model.Localizacao;

@LoggedIn
@Resource
public class AdminLocalizacaoController {
	
	private final Result result;
	private final LocalizacaoDAO dao;
	
	public AdminLocalizacaoController(Result result, LocalizacaoDAO dao) {
		super();
		this.result = result;
		this.dao = dao;
		result.include("menu", "localizacao");
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/localizacao/list")
	public void list(){
		result.include("submenu", "todas");
		result.include("qtdLocalizacoes", dao.count());
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/localizacao/enderecoDuplicado")
	public void enderecoDuplicado(){
		result.include("menu", "enderecoDuplicado");
		result.include("groupLocal", dao.findGroupLocalizacaoByLatLng());
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/localizacao/listAprovado")
	public void listAprovado(){
		result.include("submenu", "aprovado");
		result.include("qtdLocalizacoes", dao.countByAprovado(true));
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/localizacao/listPendente")
	public void listPendente(){
		result.include("submenu", "pendente");
		result.include("qtdLocalizacoes", dao.countByAprovado(false));
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/localizacao/alterar/{id}")
	public void alterar(Long id){
	//	result.use(Results.logic()).redirectTo(LocalizacaoController.class).form(id);
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/localizacao/excluir/{id}")
	public void excluir(Long id){
		dao.delete(dao.findById(id));
		result.include("mensagem", "Localização Excluida com Sucesso!");
		result.use(Results.logic()).redirectTo(AdminLocalizacaoController.class).list();
	}
	
	@Path("/admin/localizacao/pagination/{qtdResult}/{page}")
	public List<Localizacao> pagination(int qtdResult, int page){
		return dao.findAll(qtdResult, page);
	}
	
	@Path("/admin/localizacao/pagination/{qtdResult}/{page}/{status}")
	public List<Localizacao> pagination(int qtdResult, int page, boolean status){
		return dao.findSimpleLocalizacaoByAprovado(qtdResult, page, status);
	}

}
