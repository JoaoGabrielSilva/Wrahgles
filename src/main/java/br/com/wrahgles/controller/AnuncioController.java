package br.com.wrahgles.controller;

import org.apache.commons.mail.EmailException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.wrahgles.annotation.ReturnPageAfterLogin;
import br.com.wrahgles.email.Email;
import br.com.wrahgles.email.TemplateEmail;
import br.com.wrahgles.model.Contato;
import br.com.wrahgles.model.Usuario;
import br.com.wrahgles.model.UsuarioLogado;
import br.com.wrahgles.properties.ContatoDenunciaProperties;
import br.com.wrahgles.util.ValidaUtil;

@Resource
public class AnuncioController {
	
	private final Result result;
	private final Email email;
	private final TemplateEmail template;
	private final Validator validator;
	private final UsuarioLogado usuarioLogado;
	private final ValidaUtil valida;
	private final ContatoDenunciaProperties properties;
	
	public AnuncioController(Result result, Email email,
			TemplateEmail template, Validator validator,
			UsuarioLogado usuarioLogado, ValidaUtil valida,
			ContatoDenunciaProperties properties) {
		this.result = result;
		this.email = email;
		this.template = template;
		this.validator = validator;
		this.usuarioLogado = usuarioLogado;
		this.valida = valida;
		this.properties = properties;
		this.result.include("menu", "anuncios");
	}
	
	@ReturnPageAfterLogin
	public void form(){
		Contato contato = new Contato();
		
		if(usuarioLogado.getUsuario() != null){
			Usuario user = usuarioLogado.getUsuario();
			contato.setNome(user.getNome());
			contato.setEmail(user.getEmail());
		}
		result.include("contato", contato);
	}
	
	@Get
	@Path("/anuncio/linkPatrocinado")
	public void linkPatrocinado(){
	}
	
	@Post
	@Path("/anuncio/enviar")
	public void enviar(Contato contato){
		validaForm(contato);
		
		try{
			
			email.emailHtml("contato@wrahgles.com.br", properties.getPatrocinado(), template.getEmailAnuncio() +
					"Nome: " + contato.getNome() + "<br> " + "Email: " + contato.getEmail()
					+ " <br> " + "Telefone: " + contato.getTelefone() + "<br><br> " + "Mensagem: " + contato.getMensagem() + template.getEmailAnuncioRodape());
		}catch(EmailException e){
			
		}
		
		result.include("mensagem", properties.getEquipe_contato());
		result.use(Results.logic()).redirectTo(HomeController.class).home();
		
	}
	
	private void validaForm(Contato contato){
		
		if(contato.getNome().trim().isEmpty())
			validator.add(new ValidationMessage(properties.getCampo_nome_obrigatorio(), "error"));
		
		if(contato.getEmail().trim().isEmpty())
			validator.add(new ValidationMessage(properties.getCampo_email_obrigatorio(), "error"));
		else
			if(!valida.validaEmail(contato.getEmail().trim())){
				validator.add(new ValidationMessage(properties.getEmail_valido(), "error"));
			}
		
		if(contato.getMensagem().trim().isEmpty())
			validator.add(new ValidationMessage(properties.getCampo_mensagem_obrigatorio(), "error"));
		
		validator.onErrorUse(Results.page()).of(AnuncioController.class).form();
	}
	
	

}
