package br.com.wrahgles.email;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Propriedade {
	
	//atributos de classe
	private String emailSmtp;
	private String emailUser;
	private String emailSenha;
	private String emailRemetente;
	private String emailDesRemetente;
	
	public Propriedade() throws IOException{
		
		Properties props = new Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream("conf/conf.properties");		
		props.load(in);
		in.close();
		
		//joga o valor encontrado nos atributos da classe
		
		this.emailSmtp         = props.getProperty("emailSmtp");
		this.emailUser         = props.getProperty("emailUser");
		this.emailSenha        = props.getProperty("emailSenha");
		this.emailRemetente    = props.getProperty("emailRemetente");
		this.emailDesRemetente = props.getProperty("emailDesRemetente");
		
	}

	public String getEmailSmtp() {
		return emailSmtp;
	}

	public void setEmailSmtp(String emailSmtp) {
		this.emailSmtp = emailSmtp;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getEmailSenha() {
		return emailSenha;
	}

	public void setEmailSenha(String emailSenha) {
		this.emailSenha = emailSenha;
	}

	public String getEmailRemetente() {
		return emailRemetente;
	}

	public void setEmailRemetente(String emailRemetente) {
		this.emailRemetente = emailRemetente;
	}

	public String getEmailDesRemetente() {
		return emailDesRemetente;
	}

	public void setEmailDesRemetente(String emailDesRemetente) {
		this.emailDesRemetente = emailDesRemetente;
	}
	
	
	
	
	
	
	

}
