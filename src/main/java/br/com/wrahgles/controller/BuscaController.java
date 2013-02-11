package br.com.wrahgles.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.wrahgles.bean.query.LocalizacaoQuery;
import br.com.wrahgles.dao.LocalizacaoDAO;
import br.com.wrahgles.dao.UsuarioDAO;
import br.com.wrahgles.model.Bounds;
import br.com.wrahgles.model.Localizacao;
import br.com.wrahgles.model.Usuario;
import br.com.wrahgles.session.BuscaSession;

@Resource
public class BuscaController {

	private final Result result;
	private final LocalizacaoDAO dao;
	private final UsuarioDAO daoUsuario;
	private final BuscaSession buscaSession;
	
	public BuscaController(Result result, LocalizacaoDAO dao,
			UsuarioDAO daoUsuario, BuscaSession buscaSession) {
		this.result = result;
		this.dao = dao;
		this.daoUsuario = daoUsuario;
		this.buscaSession = buscaSession;
		
	}
	
	//esta URI chamada apenas por um metodo javascript que apos a renderizacao do mapa passa as coordenadas do mapa para que
	//seja realizada uma busca de localizacao que estam dentro da regiao do mapa exibido na view
	
	@Get
	@Path("/buscaJSON/{bounds.sul.latitude}/{bounds.sul.longitude}/{bounds.norte.latitude}/{bounds.norte.longitude}/{qtdResult}/{page}")
	public void buscaJSON(Bounds bounds, int qtdResult, int page){
		List<LocalizacaoQuery> locais;
		if(buscaSession.getCategoria() != null)
			locais = dao.findLocalizacaoByDescricaoInLatLng(buscaSession.getCategoria(), bounds, qtdResult, page);
		else
			locais = dao.findLocalizacaoByDescricaoInLatLng(buscaSession.getCategoriaRandon(), bounds, qtdResult, page);
		
		result.use(json()).from(locais).include("local").serialize();
		
	}
	
	@Get
	@Path("/countBuscaJSON/{bounds.sul.latitude}/{bounds.sul.longitude}/{bounds.norte.latitude}/{bounds.norte.longitude}")
	public void countBuscaJSON(Bounds bounds){
		Localizacao local = new Localizacao();
		if(buscaSession.getCategoria() != null)
			local.setNome(dao.countLocalizacaoByDescricaoInLarLng(buscaSession.getCategoria(), bounds) + "");
		else
			local.setNome(dao.countLocalizacaoByDescricaoInLarLng(buscaSession.getCategoriaRandon(), bounds) + "");
		
		result.use(json()).from(local).serialize();
	}

	@Path("/busca/users")
	public void buscaUsers(String name){
		if(name == null || name.trim().isEmpty()){
			result.include("count", daoUsuario.count());
		}else {
			result.include("count", daoUsuario.countByNomeOrEmail(name));
		}
		
		result.include("usuariosMaisComentarios", daoUsuario.findWithMoreComentario(5));
    	result.include("maisComentados", dao.findLocalizacaoWithMoreComentario(10));
		result.include("newUsers", daoUsuario.findAllOrderDtCadastro(5, 0));
		result.include("name", name);
	}
	
	@Get
	@Path("/busca/paginationUsers/{qtdResult}/{page}")
	public List<Usuario> paginationUsers(int qtdResult, int page, String busca){
		if(busca == null || busca.trim().isEmpty()){
			return daoUsuario.findAllOrderDtCadastro(qtdResult, page);
		}else {
			return daoUsuario.findByNomeOrEmailOrderDtUltimoAcesso(busca, qtdResult, page);
		}
	}
	
}
