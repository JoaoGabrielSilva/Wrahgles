package br.com.wrahgles.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sample.contacts.ContactsExample;
import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.wrahgles.annotation.ReturnPageAfterLogin;
import br.com.wrahgles.dao.ConviteDAO;
import br.com.wrahgles.dao.LocalizacaoDAO;
import br.com.wrahgles.dao.UsuarioAmigoDAO;
import br.com.wrahgles.dao.UsuarioDAO;
import br.com.wrahgles.exception.UsuarioAdicionaEleMesmoException;
import br.com.wrahgles.exception.UsuarioDeveConfirmarAmigoException;
import br.com.wrahgles.exception.UsuarioJaFoiAdicionadoException;
import br.com.wrahgles.exception.UsuarioNaoExisteException;
import br.com.wrahgles.exception.UsuarioNaoTeAdicionouException;
import br.com.wrahgles.facade.PerfilInterface;
import br.com.wrahgles.model.Convite;
import br.com.wrahgles.model.Usuario;
import br.com.wrahgles.model.UsuarioAmigoPK;
import br.com.wrahgles.model.UsuarioLogado;
import br.com.wrahgles.properties.PerfilProperties;
import br.com.wrahgles.session.ConviteSession;

import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.Email;

@Resource
public class ConviteController {
	
	private final Result result;
	private final LocalizacaoDAO dao;
	private final ConviteDAO daoConvite;
	private final UsuarioDAO daoUsuario;
	private final UsuarioAmigoDAO daoUsuarioAmigo;
	private final UsuarioLogado usuarioLogado;
	private final PerfilProperties properties;
	private final ConviteSession sessionConvite;
	private final PerfilInterface facadePerfil;
	
	
	public ConviteController(Result result, LocalizacaoDAO dao,
			ConviteDAO daoConvite, UsuarioDAO daoUsuario,
			UsuarioAmigoDAO daoUsuarioAmigo, UsuarioLogado usuarioLogado,
			PerfilProperties properties, ConviteSession sessionConvite,
			PerfilInterface facadePerfil) {
		super();
		this.result = result;
		this.dao = dao;
		this.daoConvite = daoConvite;
		this.daoUsuario = daoUsuario;
		this.daoUsuarioAmigo = daoUsuarioAmigo;
		this.usuarioLogado = usuarioLogado;
		this.properties = properties;
		this.sessionConvite = sessionConvite;
		this.facadePerfil = facadePerfil;
	}
	
	private void populaColDir(){
		result.include("usuarioMaisComentarios", daoUsuario.findWithMoreComentario(5));
		result.include("maisComentados", dao.findLocalizacaoWithMoreComentario(10));
		result.include("newUsers", daoUsuario.findAllOrderDtCadastro(5, 0));
		
	}
	
	@ReturnPageAfterLogin
	@LoggedIn
	public void form(){
		this.populaColDir();
	}
	
	public void listPerfil(String conta, String email, String senha){
		List<Usuario> listUsuario = new ArrayList<Usuario>();
		List<String> listConvites = new ArrayList<String>();
		UsuarioAmigoPK pk = new UsuarioAmigoPK();
		pk.setUsuario(usuarioLogado.getUsuario());
		try{
			//regra para busca de contatos do Gmail
			if(conta.trim().equalsIgnoreCase("google")){
				ContactsExample contactGmail = new ContactsExample();
				ContactFeed contactFeed = contactGmail.getContacts(email.trim(), senha.trim());
				
				for(ContactEntry entry: contactFeed.getEntries())
					for(Email emailAdd: entry.getEmailAddresses()){
						pk.setUsuarioAmigo(daoUsuario.findByEmail(emailAdd.getAddress().trim()));
						if(pk.getUsuarioAmigo() != null && daoUsuarioAmigo.findById(pk) == null && listUsuario.indexOf(pk.getUsuarioAmigo()) == -1){
							listUsuario.add(pk.getUsuarioAmigo());
						}else if(pk.getUsuarioAmigo() == null && listConvites.indexOf(emailAdd.getAddress().trim()) == -1){
							listConvites.add(emailAdd.getAddress().trim());
						}
					}
			}
			//popula coluna direita
			this.populaColDir();
			
			sessionConvite.setListConvites(listConvites);
			//direciona para pagina de contatos ja cadastrados no wrahgles
			if(listUsuario != null && !listUsuario.isEmpty()){
				result.include("listAmigos", listUsuario);
			//direciona para a pagian de contatos q nao estao cadastrados no Wrahgles
			}else {
				result.use(Results.logic()).redirectTo(ConviteController.class).listConvites();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@LoggedIn
	public void listConvites(){
		this.populaColDir();
		
		if(sessionConvite.getListConvites() != null && !sessionConvite.getListConvites().isEmpty()){
			result.include("qtdConvites", sessionConvite.getListConvites().size());
			result.include("listConvites", sessionConvite.getListConvites());
			
		}else {
			result.use(Results.logic()).redirectTo(PerfilController.class).meuPerfil();
		}
	}
	
	@LoggedIn
	@Post
	public void enviarConvites(String[] contato, String mensagem){
		for(String email: contato){
			if(email.isEmpty())
			  continue;
			
			Convite conBD = daoConvite.findByIdUsuarioAndEmail(usuarioLogado.getUsuario().getId(), email.trim());
			if(conBD == null) {
				Convite con = new Convite();
				con.setUsuario(usuarioLogado.getUsuario());
				con.setEmail(email.trim());
				con.setDtConvite(new Date());
				con.setFlgEnvioEmail(true);
				
				daoConvite.save(con);
			}
			
		}
		
		result.include("mensagem", "Você convidou seus amigos para entrarem no Wrahgles!");
		result.use(Results.logic()).redirectTo(PerfilController.class).meuPerfil();
	}
	
	@LoggedIn
	@Post
	public void addAmigos(String[] listAddAmigos){
		boolean addAmigo = false;
		
		for(String cod: listAddAmigos){
			if(cod.isEmpty())
			  continue;
			addAmigo = true;
			
			try{
				facadePerfil.adicionarAmigo(usuarioLogado.getUsuario(), new Long(cod));
			} catch (UsuarioNaoExisteException e1) {
				e1.printStackTrace();
			} catch (UsuarioAdicionaEleMesmoException e1) {
				e1.printStackTrace();
			} catch (UsuarioJaFoiAdicionadoException e1) {
				e1.printStackTrace();
			// CONFIRMAR AMIZADE!	
		} catch (UsuarioDeveConfirmarAmigoException e1) {
			try {
				facadePerfil.confirmarAmigo(usuarioLogado.getUsuario(), new Long(cod));
			} catch (UsuarioNaoExisteException e) {
				e.printStackTrace();
			} catch (UsuarioAdicionaEleMesmoException e) {
				e.printStackTrace();
			} catch (UsuarioNaoTeAdicionouException e) {
				e.printStackTrace();
			} catch (UsuarioJaFoiAdicionadoException e) {
				e.printStackTrace();
			}
		}			
	}
		if(addAmigo)
		   result.include("mensagem", properties.getConf_amizade());
		   result.use(Results.logic()).redirectTo(ConviteController.class).listConvites();
		}
		
		
	

}
