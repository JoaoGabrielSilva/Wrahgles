package br.com.wrahgles.controller;

import br.com.bronx.vraptor.restrictrex.annotation.AccessDeniedPage;
import br.com.bronx.vraptor.restrictrex.annotation.LoginPage;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.wrahgles.annotation.ReturnPageAfterLogin;
import br.com.wrahgles.model.Usuario;
import br.com.wrahgles.session.BuscaSession;
import br.com.wrahgles.util.CategoriaUtil;

@Resource
public class HomeController {
	
	private final Result result;
	private final BuscaSession buscaSession;
	private final CategoriaUtil categoria;
	
	public HomeController(Result result, BuscaSession buscaSession, CategoriaUtil categoria){
		this.result = result;
		this.buscaSession = buscaSession;
		this.categoria = categoria;
		this.result.include("menu", "home");
	}
	
	@Path("/")
	@ReturnPageAfterLogin
	public void home(){
		String cat = categoria.getNameCat();
		buscaSession.setCategoriaRandon(cat);
	}
	
	@Path("/busca/{zomm}")
	@ReturnPageAfterLogin
	@Post
	public void busca(int zoom, String enderecoPesquisa,String categoriaPerquisa) {
		result.include("enderecoPesquisa", enderecoPesquisa);
		result.include("zoomPesquisa", zoom);
		buscaSession.setCategoria(categoriaPerquisa);
		
	}
	
	@Path("/busca/{zoom}")
	@ReturnPageAfterLogin
	@Get
	public void buscaGet(int zoom) {
		result.use(Results.logic()).redirectTo(HomeController.class).home();
	}

	@LoginPage
	public void logar(){
		Usuario user = new Usuario();
		user.setEmail("");
		user.setSenhaTemp("");
		result.include("usuario", user);
	}
	
	@Path("/home/senha")
	public void senha(){
		
	}
	
	@AccessDeniedPage
	@ReturnPageAfterLogin
	public void acessoNegado(){
		
	}
	

}
