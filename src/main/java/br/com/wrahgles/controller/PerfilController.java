package br.com.wrahgles.controller;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.wrahgles.annotation.ReturnPageAfterLogin;
import br.com.wrahgles.dao.ComentarioDAO;
import br.com.wrahgles.dao.LocalizacaoFotoDAO;
import br.com.wrahgles.dao.UsuarioAmigoDAO;
import br.com.wrahgles.dao.UsuarioDAO;
import br.com.wrahgles.exception.UsuarioAdicionaEleMesmoException;
import br.com.wrahgles.exception.UsuarioDeveConfirmarAmigoException;
import br.com.wrahgles.exception.UsuarioJaFoiAdicionadoException;
import br.com.wrahgles.exception.UsuarioNaoExisteException;
import br.com.wrahgles.exception.UsuarioNaoTeAdicionouException;
import br.com.wrahgles.facade.PerfilInterface;
import br.com.wrahgles.model.Usuario;
import br.com.wrahgles.model.UsuarioAmigo;
import br.com.wrahgles.model.UsuarioAmigoPK;
import br.com.wrahgles.model.UsuarioLogado;
import br.com.wrahgles.properties.PerfilProperties;

@Resource
public class PerfilController {
	private final UsuarioLogado usuarioLogado;
    private final Result result;
    private final UsuarioDAO dao;
    private final UsuarioAmigoDAO daoAmigo;
    private final ComentarioDAO daoComentario;
    private final LocalizacaoFotoDAO daoFotoLocal;
    private final Validator validator;
    private final PerfilProperties properties;
    private final PerfilInterface facadePerfil;
    
    
    
	public PerfilController(UsuarioLogado usuarioLogado, Result result,
			UsuarioDAO dao, UsuarioAmigoDAO daoAmigo,
			ComentarioDAO daoComentario, LocalizacaoFotoDAO daoFotoLocal,
			Validator validator, PerfilProperties properties,
			PerfilInterface facadePerfil) {
		super();
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.dao = dao;
		this.daoAmigo = daoAmigo;
		this.daoComentario = daoComentario;
		this.daoFotoLocal = daoFotoLocal;
		this.validator = validator;
		this.properties = properties;
		this.facadePerfil = facadePerfil;
	}


	@Override
	public String toString() {
		return "PerfilController [usuarioLogado=" + usuarioLogado + ", result="
				+ result + ", dao=" + dao + ", daoAmigo=" + daoAmigo
				+ ", daoComentario=" + daoComentario + ", daoFotoLocal="
				+ daoFotoLocal + ", validator=" + validator + ", properties="
				+ properties + ", facadePerfil=" + facadePerfil + "]";
	}
    
	
	@LoggedIn
    @ReturnPageAfterLogin
    @Path("/perfil/meuPerfil")
	public void meuPerfil(){
		result.use(Results.logic()).redirectTo(PerfilController.class).perfil(usuarioLogado.getUsuario().getId(),usuarioLogado.getUsuario().getNomeFormat());
	}
	
	
	@ReturnPageAfterLogin
	@Path("/perfil/{id}/{nome}")
	public void perfil(Long id, String nome){
		//includes default dependentes para o menu e coluna direita
		includesDefaultPerfil(id);
		
		result.include("comentarios", daoComentario.findComentarioByUsuarioId(id));
	}
	
	private void includesDefaultPerfil(Long id){
		if(usuarioLogado.getUsuario() != null) {
			//aba de solicitações pendentes
			if(usuarioLogado.getUsuario().getId().longValue() == id.longValue()){
				result.include("qtdAmigosPendentes", daoAmigo.countSolicitacoesPendentes(id));
				result.include("amigosPendentes", daoAmigo.listSolicitacoesPendentes(id));
				result.include("statusPerfil", "meuPerfil");
			}else {
				UsuarioAmigoPK pk = new UsuarioAmigoPK();
				pk.setUsuario(usuarioLogado.getUsuario());
				pk.setUsuarioAmigo(dao.findById(id));
				
				UsuarioAmigoPK pkOther = new UsuarioAmigoPK();
				pkOther.setUsuario(pk.getUsuarioAmigo());
				pkOther.setUsuarioAmigo(pk.getUsuario());
				
				UsuarioAmigo amigo = daoAmigo.findById(pk);
				UsuarioAmigo amigoOther = daoAmigo.findById(pkOther);
				
				if(amigo != null && amigoOther != null)
				   result.include("statusPerfil", "amigo");
				else if(amigo != null)
				   result.include("statusPerfil", "aguardandoAmizade");
				else if(amigoOther != null)
				   result.include("statusPerfil", "confirmarAmigo");
			}
		}
		
		result.include("usuario", dao.findById(id));
		result.include("qtdComentarios", daoComentario.countComentarioByUsuarioId(id));
		result.include("fotos", daoFotoLocal.findByIdUsuario(id, 9));
		result.include("qtdFotos", daoFotoLocal.countByIdUsuario(id, true));
		result.include("amigos", daoAmigo.listAmigos(id, true,9));
		result.include("qtdAmigos", daoAmigo.countAmigos(id, true));
	}
	
	@ReturnPageAfterLogin
	@LoggedIn
	@Path("/perfil/addAmigo/{id}")
	public void addAmigo(Long id){
		Usuario user = dao.findById(id);
		
		
		try{
			facadePerfil.adicionarAmigo(usuarioLogado.getUsuario(), id);
			
			result.include("mensagem", properties.getConf_amizade());
			result.use(Results.logic()).redirectTo(PerfilController.class).perfil(id, user.getNomeFormat());
		}catch(UsuarioNaoExisteException e1){
			result.include("mensagem", "Usuário não existe!");
			validator.add(new ValidationMessage("", "error"));
			validator.onErrorUse(Results.logic()).redirectTo(PerfilController.class).perfil(usuarioLogado.getUsuario().getId(), usuarioLogado.getUsuario().getNomeFormat());
		}catch(UsuarioAdicionaEleMesmoException e1){
			result.include("mensagem", properties.getErro_add_voce_mesmo());
			validator.add(new ValidationMessage("", "error"));
			validator.onErrorUse(Results.logic()).redirectTo(PerfilController.class).perfil(id, user.getNomeFormat());
			
		}catch(UsuarioJaFoiAdicionadoException e1){
			result.include("mensagem", properties.getErro_add_usuario());
			validator.add(new ValidationMessage("","error"));
			validator.onErrorUse(Results.logic()).redirectTo(PerfilController.class).perfil(id, user.getNomeFormat());
		}catch(UsuarioDeveConfirmarAmigoException e1){
			result.use(Results.logic()).redirectTo(PerfilController.class).confirmaAmigo(id);
		}
	}
	
	@ReturnPageAfterLogin
	@LoggedIn
	@Path("/perfil/confirmaAmigo/{id}")
	public void confirmaAmigo(Long id){
		Usuario user = dao.findById(id);
		
		try{
			facadePerfil.confirmarAmigo(usuarioLogado.getUsuario(), id);
			
			result.include("mensagem", properties.getConf_locais() + " " + user.getNome() + " " + properties.getConf_frequencia());
			result.use(Results.logic()).redirectTo(PerfilController.class).perfil(id, user.getNomeFormat());
			
		}catch(UsuarioNaoExisteException e1){
			result.include("mensagem", properties.getUsuario_nao_existe());
			validator.add(new ValidationMessage("", "error"));
			validator.onErrorUse(Results.logic()).redirectTo(PerfilController.class).perfil(usuarioLogado.getUsuario().getId(), usuarioLogado.getUsuario().getNomeFormat());
			
		} catch (UsuarioAdicionaEleMesmoException e1) {
			result.include("mensagem", properties.getErro_conf_voce_mesmo());
			validator.add(new ValidationMessage("", "error"));
			validator.onErrorUse(Results.logic()).redirectTo(PerfilController.class).perfil(id, user.getNomeFormat());
			
		// USUARIO NAO TE ADICIONOU	
		} catch (UsuarioNaoTeAdicionouException e1) {
			result.include("mensagem", properties.getErro_add_conf_amizade());
			validator.add(new ValidationMessage("", "error"));
			validator.onErrorUse(Results.logic()).redirectTo(PerfilController.class).perfil(id, user.getNomeFormat());
			
		// USUARIO JA FOI ADICIONADO A SUA LISTA DE AMIGOS	
		} catch (UsuarioJaFoiAdicionadoException e1) {
			result.include("mensagem", user.getNome() + " " + properties.getErro_add_lista_amigo());
			validator.add(new ValidationMessage("", "error"));
			validator.onErrorUse(Results.logic()).redirectTo(PerfilController.class).perfil(id, user.getNomeFormat());
		}
		
	}
	
	@ReturnPageAfterLogin
	@LoggedIn
	@Path("/perfil/recusarAmigo/{id}")
	public void recusarAmigo(Long id){
		Usuario user = dao.findById(id);
		if(user == null){
			result.include("mensagem", properties.getUsuario_nao_existe());
			validator.add(new ValidationMessage("", "error"));
			validator.onErrorUse(Results.logic()).redirectTo(PerfilController.class).perfil(usuarioLogado.getUsuario().getId(), usuarioLogado.getUsuario().getNomeFormat());
		}
		
		UsuarioAmigoPK pkSolicita = new UsuarioAmigoPK();
		pkSolicita.setUsuario(user);
		pkSolicita.setUsuarioAmigo(usuarioLogado.getUsuario());
	
		UsuarioAmigoPK pkConfirma = new UsuarioAmigoPK();
		pkConfirma.setUsuarioAmigo(user);
		pkConfirma.setUsuario(usuarioLogado.getUsuario());
		
		UsuarioAmigo usAmigo = daoAmigo.findById(pkSolicita);
		if(usAmigo != null && daoAmigo.findById(pkConfirma) == null) {
			daoAmigo.delete(usAmigo);
		} else {
			result.include("mensagem", properties.getErro_solicitacao());
			validator.add(new ValidationMessage("", "error"));
			validator.onErrorUse(Results.logic()).redirectTo(PerfilController.class).perfil(usuarioLogado.getUsuario().getId(), usuarioLogado.getUsuario().getNomeFormat());
		}
		
		result.use(Results.logic()).redirectTo(PerfilController.class).perfil(usuarioLogado.getUsuario().getId(), usuarioLogado.getUsuario().getNomeFormat());
		
	}
	
	
	@ReturnPageAfterLogin
	@LoggedIn
	@Path("/perfil/removerAmigo/{id}")
	public void removerAmigo(Long id){
		
		Usuario user = dao.findById(id);
		if(user == null){
			result.include("mensagem", properties.getUsuario_nao_existe());
			validator.add(new ValidationMessage("", "error"));
			validator.onErrorUse(Results.logic()).redirectTo(PerfilController.class).perfil(usuarioLogado.getUsuario().getId(), usuarioLogado.getUsuario().getNomeFormat());
			
		}
		
		UsuarioAmigoPK pkSolicita = new UsuarioAmigoPK();
		pkSolicita.setUsuario(user);
		pkSolicita.setUsuarioAmigo(usuarioLogado.getUsuario());
		
		UsuarioAmigoPK pkConfirma = new UsuarioAmigoPK();
		pkConfirma.setUsuarioAmigo(user);
		pkConfirma.setUsuario(usuarioLogado.getUsuario());
		
		UsuarioAmigo amigo = daoAmigo.findById(pkSolicita);
		UsuarioAmigo amigoOther = daoAmigo.findById(pkConfirma);
		if(amigo != null && amigoOther != null){
			daoAmigo.delete(amigo);
			daoAmigo.delete(amigoOther);
		}else {
			result.include("mensagem",properties.getErro_solicitacao());
			validator.add(new ValidationMessage("", "error"));
			validator.onErrorUse(Results.logic()).redirectTo(PerfilController.class).perfil(usuarioLogado.getUsuario().getId(), usuarioLogado.getUsuario().getNomeFormat());
		}
		
		result.include("mensagem", user.getNome() + " " + properties.getRemovido_lista_amigo());
		result.use(Results.logic()).redirectTo(PerfilController.class).perfil(usuarioLogado.getUsuario().getId(), usuarioLogado.getUsuario().getNomeFormat());
		
	}

}
