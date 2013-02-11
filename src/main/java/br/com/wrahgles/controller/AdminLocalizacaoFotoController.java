package br.com.wrahgles.controller;

import java.io.File;
import java.util.List;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.RequestInfo;
import br.com.wrahgles.annotation.ReturnPageAfterLogin;
import br.com.wrahgles.dao.LocalizacaoFotoDAO;
import br.com.wrahgles.model.LocalizacaoFoto;

@LoggedIn
@Resource
public class AdminLocalizacaoFotoController {
	private final Result result;
	private final RequestInfo info;
	private final LocalizacaoFotoDAO dao;
	
	public AdminLocalizacaoFotoController(Result result, RequestInfo info,
			LocalizacaoFotoDAO dao) {
		super();
		this.result = result;
		this.info = info;
		this.dao = dao;
		result.include("menu", "localizacaoFoto");
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/localizacaoFoto/list")
	public void list(){
		result.include("submenu", "todas");
		result.include("qtdLocalizacoes", dao.count());
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/localizacaoFoto/listAprovado")
	public void listAprovado(){
		result.include("submenu", "aprovado");
		result.include("qtdLocalizacoes", dao.countByAprovado(true));
		
	}	
	
	@ReturnPageAfterLogin
	@Path("/admin/localizacaoFoto/listPendente")
	public void listPendente(){
		result.include("submenu", "pendente");
		result.include("qtdLocalizacoes", dao.countByAprovado(false));
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/localizacaoFoto/alterar/{id}")
	public void alterar(Long id){
		result.include("localFoto", dao.findById(id));
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/localizacaoFoto/salvar")
	public void salvar(LocalizacaoFoto localizacaoFoto){
		LocalizacaoFoto l = dao.findById(localizacaoFoto.getId());
		l.setFlgAtivo(localizacaoFoto.isFlgAtivo());
		
		dao.saveOrUpdate(l);
		result.redirectTo(this).list();
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/localizacaoFoto/excluir/{id}")
	public void excluir(Long id){
		LocalizacaoFoto foto = dao.findById(id);
		
		//deleta foto caso exista
		File fUpdate = new File(info.getServletContext().getRealPath(foto.getImagem()));
		if(fUpdate.isFile() && fUpdate.exists())
			fUpdate.delete();
		
		dao.delete(foto);
		result.include("mensagem", "Foto da localizacao excluida com sucesso!");
		result.redirectTo(this).list();
		
	}
	
	@Path("/admin/localizacaoFoto/pagination/{qtdResult}/{page}")
	public List<LocalizacaoFoto> pagination(int qtdResult , int page){
		return dao.findAll(qtdResult, page);
	}
	
	@Path("/admin/localizacaoFoto/pagination/{qtdResult}/{page}/{status}")
	public List<LocalizacaoFoto> pagination(int qtdResult, int page, boolean status){
		return dao.findByAprovado(qtdResult, page, status);
	}
	
	
}
