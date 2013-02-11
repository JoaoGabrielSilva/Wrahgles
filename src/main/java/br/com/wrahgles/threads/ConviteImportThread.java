package br.com.wrahgles.threads;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.mail.EmailException;

import br.com.wrahgles.email.Email;
import br.com.wrahgles.email.TemplateEmail;
import br.com.wrahgles.model.Convite;
import br.com.wrahgles.util.DataUtil;

public class ConviteImportThread extends BaseThread{

	private Convite convite;
	
	public ConviteImportThread(Convite convite){
		this.convite = convite;
	}
	
	@Override
	public void run() {
       try{
    	   processarConvite(convite);
       }finally {
    	   ManagerEmail.threadConviteRunning = ManagerEmail.threadConviteRunning - 1;
       }
	}
	
	public void processarConvite(Convite convite){
		String origemFoto;
		
		if(convite.getUsuario().getFotoPerfil() == null){
			origemFoto = "http://www.wrahgles.com.br/images/default.jpg";
		}else{
			origemFoto = "http://www.wrahgles.com.br/" + convite.getUsuario().getFotoPerfil();
		}
		
		TemplateEmail template;
		Email email;
		
		try{
			template = new TemplateEmail();
			email = new Email();
			email.emailHtml(convite.getEmail(), convite.getUsuario().getNome() + "convidou você para entrar no Wrahgles",
		    template.getEmailPerfilConviteCabecalho() + template.getEmailPerfilConviteCorpo1() +
		    template.getEmailPerfilAddAmigoFoto_src() + origemFoto + template.getEmailPerfilAddAmigoFoto_alt() +
		    convite.getUsuario().getNome() + template.getEmailPerfilAddAmigoFoto_title() + convite.getUsuario().getNome() +
		    template.getEmailPerfilAddAmigoCorpo3() + DataUtil.formatarDataTimestampSegundos(new Date()) +
		    template.getEmailPerfilConviteCorpo4() + convite.getEmail() + template.getEmailPerfilConviteRodape());
		    
		}catch(EmailException e){
			
		}catch(IOException e1){
			
		}
		
		
	}

}
