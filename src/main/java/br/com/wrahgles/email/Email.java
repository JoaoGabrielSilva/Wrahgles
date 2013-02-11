package br.com.wrahgles.email;

import java.io.IOException;

import javax.mail.Authenticator;
import javax.mail.Multipart;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class Email {
	
	private String destinario;
	private String assunto;
	private String mensagem;
	private String arquivo;
	
	//detalhes configuracao
	
	private String SMTP;
	private String USER;
	private String PASSWD;
	private String REMETENTE;
	private String DES_REMETENTE;
	
	
	
	
	public Email() throws IOException{
		
		Propriedade p      = new Propriedade();   
		
		this.SMTP    	   = p.getEmailSmtp();
		this.USER    	   = p.getEmailUser();
		this.PASSWD  	   = p.getEmailSenha();
		this.REMETENTE     = p.getEmailRemetente();
		this.DES_REMETENTE = p.getEmailDesRemetente();
		
		
	}
	
	//modo de envio de email simples - emailSimples
	
	
	public boolean emailSimples(String destinatario, String assunto, String mensagem)throws EmailException{
		
		
		try{
			
			this.destinario = destinatario;
			this.assunto    = assunto;
			this.mensagem   = mensagem;
			
			if(!this.validarEmail()){
				throw new EmailException("Email - parametros do email inválidos");
				
			}else{
				
				SimpleEmail email = new SimpleEmail();
				
				//SMTP
				email.setHostName(SMTP);
				//Autenticação
				email.setAuthenticator(new Autenticar(USER,PASSWD));
				//destinatario
				email.addTo(this.destinario, this.destinario);
				//Remetente
				email.setFrom(REMETENTE, DES_REMETENTE);
				//assunto
				email.setSubject(this.assunto);
				//mensagem
				email.setMsg(this.mensagem);
				//emvia email
				email.send();
				
				return true;
				
			}
			
		}catch(EmailException e){
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	//envio de email em formato HTML
	public boolean emailHtml(String destinatario, String assunto, String mensagem) throws EmailException{
		
		try{
			
			this.destinario = destinatario;
			this.assunto    = assunto;
			this.mensagem   = mensagem;
			
			if(!validarEmail()){
				throw new EmailException("Email - Parametro do email invalido");
			}
			else {
				HtmlEmail email = new HtmlEmail();
				
				//SMTP
				email.setHostName(SMTP);
				//Seta o tipo do texto
				email.setContent(this.mensagem, "text/html");
				//Autenticação
				email.setAuthenticator(new Autenticar(USER, PASSWD)); 
				//destinatario
				email.addTo(this.destinario, this.destinario);
				//remetente
				email.setFrom(REMETENTE, DES_REMETENTE);
				//assunto
				email.setSubject(this.assunto);
				//mensagem em HTML
				email.setHtmlMsg(this.mensagem);
				//enva 0 email
				email.send();
				
				return true;
				
			}
			
		}catch(EmailException e){
			e.printStackTrace();
		}
		return false;
		
	}
	
	//envio de email com anexo
	public boolean emailAnexo(String destinatario, String assunto, String mensagem, String arquivo) throws EmailException{
		
		try{
			
			this.destinario = destinatario;
			this.assunto    = assunto;
			this.mensagem   = mensagem;
			this.arquivo    = arquivo;
			
			if(!this.validarEmail()){
				throw new EmailException("Email - parametros do email invalidos");
			}
			else{
				EmailAttachment attachment = new EmailAttachment();
				
				//caminho para o arquivo ser anexado
				attachment.setPath(this.arquivo);
				//Anexa o arquivo
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				
				MultiPartEmail email = new MultiPartEmail();
				//SMTP
				email.setHostName(SMTP);
				//autentição
				email.setAuthenticator(new Autenticar(USER,PASSWD));
				//destinatario
				email.addTo(this.destinario, this.destinario);
				//remetente
				email.setFrom(REMETENTE, DES_REMETENTE);
				//assunto
				email.setSubject(this.assunto);
				//mensagem no corpo do email
				email.setMsg(this.mensagem);
				//anexsa o arquivo no email
				email.attach(attachment);
				//envia o email
				email.send();
				
				return true;
			}
		}catch(EmailException e){
		    e.printStackTrace();
		    throw new EmailException("Email - paramentro do emaim invalidos");
		}
	}
	
	private boolean validarEmail(){
		
		if((this.destinario == null) || (this.assunto == null) || (this.mensagem == null)){
			return false;
		}
		else if((this.destinario.indexOf("@") == -1) || (this.destinario.indexOf(".") == -1)){
			return false;
		}
		else{
			return true;
		}
		
		
	}
	

}
