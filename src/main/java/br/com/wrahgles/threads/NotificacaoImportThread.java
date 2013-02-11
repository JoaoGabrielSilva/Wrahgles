package br.com.wrahgles.threads;

import java.io.IOException;

import org.apache.commons.mail.EmailException;

import br.com.wrahgles.email.Email;
import br.com.wrahgles.email.TemplateEmail;
import br.com.wrahgles.model.Notificacao;
import br.com.wrahgles.model.Usuario;

public class NotificacaoImportThread extends BaseThread {
 
	private Notificacao notificacao;
	private Usuario user;
	
	public NotificacaoImportThread(Notificacao notificacao, Usuario user){
		this.notificacao = notificacao;
		this.user = user;
	}
	
	
	@Override
	public void run() {
		try{
			processarNotificacao(notificacao, user);
		}finally{
			ManagerEmail.threadNotificacaoRunning = ManagerEmail.threadNotificacaoRunning - 1;
		}
	}
	
	public void processarNotificacao(Notificacao notificacao, Usuario user){
		Email email;
		TemplateEmail template;
		
		try{
			template = new TemplateEmail();
			email = new Email();
			
			email.emailHtml(user.getEmail(), "[Wrahgles] " + notificacao.getTitulo(),
			template.getEmailMarketingCabecalho() + user.getNome() + template.getEmailMarketingCorpo1() + notificacao.getMensagem() +
			template.getEmailMarketingCorpo2() + user.getEmail() + template.getEmailMarketingRodape());
			Thread.sleep(500);
		}catch(EmailException e){
			
		}catch(InterruptedException e){
			
		}catch(IOException e){
			
		}
		
	}

}
