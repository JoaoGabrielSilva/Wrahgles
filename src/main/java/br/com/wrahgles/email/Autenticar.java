package br.com.wrahgles.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Autenticar extends Authenticator {

	private String usuario;
	private String senha;
	
    public Autenticar() {}
    
    
    public Autenticar(String usuario, String senha){
    	this.usuario = usuario;
    	this.senha = senha;
    }
    
    public PasswordAuthentication getPasswordAutehentication(){
    	return new PasswordAuthentication(usuario, senha);
    }
    
	
	
}
