package br.com.wrahgles.controller;

import java.util.List;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.wrahgles.annotation.ReturnPageAfterLogin;
import br.com.wrahgles.dao.DenunciaDAO;
import br.com.wrahgles.model.Denuncia;

@LoggedIn
@Resource
public class AdminDenunciaController {
	
 private final Result result;
 private final DenunciaDAO dao;
 
 
 public AdminDenunciaController(Result result, DenunciaDAO dao) {
	super();
	this.result = result;
	this.dao = dao;
	result.include("menu", "denuncia");
	
}
 @ReturnPageAfterLogin
 @Path("/admin/denuncia/list")
 public void list(){
	 result.include("submenu", "todas");
	 result.include("qtdDenuncias", dao.count());
 }
 
 @Path("/admin/denuncia/pagination/{qtdResult}/{page}")
 public List<Denuncia> paginatons(int qtdResult, int page){
	return dao.findAll(qtdResult, page);
 }
 
 
}
