package br.com.wrahgles.controller;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.wrahgles.annotation.ReturnPageAfterLogin;
import br.com.wrahgles.model.Notificacao;
import br.com.wrahgles.properties.AdminProperties;
import br.com.wrahgles.threads.ManagerEmail;

@LoggedIn
@Resource
public class AdminNotificacaoController {
	
	private final Validator validator;
	private final AdminProperties properties;
	private final ManagerEmail manageThread;
	
	public AdminNotificacaoController(Validator validator,
			AdminProperties properties, ManagerEmail manageThread) {
		super();
		this.validator = validator;
		this.properties = properties;
		this.manageThread = manageThread;
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/notificacao/form")
	public void form(){
		
	}
	
	@Path("/admin/notificacao/enviar")
	public void enviar(Notificacao notificacao) throws InterruptedException{
		validaForm(notificacao);
		manageThread.enviarNotificacaoCliente(notificacao);
	}
	
	private void validaForm(Notificacao notificacao){
		if(notificacao.getTitulo().trim().isEmpty())
			validator.add(new ValidationMessage(properties.getCampo_titulo_obrigatorio(), "error"));
		
		if(notificacao.getMensagem().trim().isEmpty())
			validator.add(new ValidationMessage(properties.getCampo_mensagem_obrigatorio(), "error"));
		
		if(!notificacao.isAtivo())
			validator.add(new ValidationMessage(properties.getSelecionar(), "error"));
		
		validator.onErrorUse(Results.page()).of(AdminNotificacaoController.class).form();
		
	}
	
	
	

}
