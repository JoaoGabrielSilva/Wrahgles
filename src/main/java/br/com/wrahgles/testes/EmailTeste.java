package br.com.wrahgles.testes;

import java.io.IOException;

import org.apache.commons.mail.EmailException;

import br.com.wrahgles.email.TemplateEmail;

public class EmailTeste {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws EmailException 
	 */
	public static void main(String[] args) throws IOException, EmailException {
     
	TemplateEmail temail = new TemplateEmail();
	
	System.out.println(temail.getEmailMarketingCabecalho());
	System.out.println(temail.getEmailMarketingCorpo1());
	System.out.println(temail.getEmailMarketingCorpo2());
	System.out.println(temail.getEmailMarketingRodape());

	}

}
