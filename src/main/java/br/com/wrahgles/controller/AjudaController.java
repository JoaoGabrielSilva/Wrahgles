package br.com.wrahgles.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;


@Resource
public class AjudaController {
	
	private final Result result;

	public AjudaController(Result result) {
		super();
		this.result = result;
		this.result.include("menu", "ajuda");
	}
	
	public void form(){}
	
	public void perguntas(){
		result.redirectTo(this).form();
	}
	
	public void termos(){
		result.include("menuTabs", "1");
		result.redirectTo(this).form();
	}
	
	public void politica(){
		result.include("menuTabs", "2");
		result.redirectTo(this).form();
	}
	

}
