package br.com.wrahgles.email;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class TemplateEmail {

	//novo usuario
	private String emailNewUserCabecalho;
	private String emailNewUserCorpo;
	private String emailNewUserRodape;
	
	//esqueceu a senha
	private String emailEsqueceuSenhaCabecalho;
	private String emailEsqueceuSenhaCorpo1;
	private String emailEsqueceuSenhaCorpo2;
	private String emailEsqueceuSenhaRodape;
	
	//marketing
	private String emailMarketingCabecalho;
	private String emailMarketingCorpo1;
	private String emailMarketingCorpo2;
	private String emailMarketingRodape;
	
	
    // perfil convite
	private String emailPerfilConviteCabecalho;
	private String emailPerfilConviteCorpo1;
	private String emailPerfilConviteFoto_src;
	private String emailPerfilConviteFoto_alt;
	private String emailPerfilConviteFoto_title;
	private String emailPerfilConviteFoto_div;
	private String emailPerfilConviteCorpo2;
	private String emailPerfilConviteCorpo3;
	private String emailPerfilConviteCorpo4;
	private String emailPerfilConviteRodape;
	
	//add amigo
	private String emailPerfilAddAmigoCabacalho;
	private String emailPerfilAddAmigoCorpo1;
	private String emailPerfilAddAmigoCorpo2;
	private String emailPerfilAddAmigoFoto_src;
	private String emailPerfilAddAmigoFoto_alt;
	private String emailPerfilAddAmigoFoto_title;
	private String emailPerfilAddAmigoFoto_div;
	private String emailPerfilAddAmigoCorpo3;
	private String emailPerfilAddAmigoCorpo4;
	private String emailPerfilAddAmigoCorpo5;
	private String emailPerfilAddAmigoCorpo6;
	private String emailPerfilAddAmigoCorpo7;
	private String emailPerfilAddAmigoCorpo8;
	private String emailPerfilAddAmigoCorpo9;
	private String emailPerfilAddAmigoRodape;
	
	//confirmacao add amigo
	private String emailPerfilAddAmigoCabecalho;
	private String emailPerfilConfAddAmigoCabecalho;
	private String emailPerfilConfAddAmigoCorpo1;
	private String emailPerfilConfAddAmigoCorpo2;
	private String emailPerfilConfAddAmigoFoto_src;
	private String emailPerfilConfAddAmigoFoto_alt;
	private String emailPerfilConfAddAmigoFoto_title;
	private String emailPerfilConfAddAmigoFoto_div;
	private String emailPerfilConfAddAmigoCorpo3;
	private String emailPerfilConfAddAmigoCorpo4;
	private String emailPerfilConfAddAmigoCorpo5;
	private String emailPerfilConfAddAmigoCorpo6;
	private String emailPerfilConfAddAmigoCorpo7;
	private String emailPerfilConfAddAmigoCorpo8; 
	private String emailPerfilConfAddAmigoCorpo9;
	private String emailPerfilConfAddAmigoRodape;
	
	
	//contato
	private String emailContato;
	private String emailContatoRodape;
    
	//anuncio
	private String emailAnuncio;
	private String emailAnuncioRodape;
	
	public TemplateEmail() throws IOException {
		
		
		Properties props = new Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream("conf/email.properties");
		props.load(in);
		in.close();
		
		//E-Mail Novo Usuario
        this.emailNewUserCabecalho     = props.getProperty("emailNewUserCabecalho");  
        this.emailNewUserCorpo         = props.getProperty("emailNewUserCorpo");  
        this.emailNewUserRodape        = props.getProperty("emailNewUserRodape"); 
		
        //E-mail Esqueceu a senha
        this.emailEsqueceuSenhaCabecalho    = props.getProperty("emailEsqueceuSenhaCabecalho");
        this.emailEsqueceuSenhaCorpo1       = props.getProperty("emailEsqueceuSenhaCorpo1");
        this.emailEsqueceuSenhaCorpo2       = props.getProperty("emailEsqueceuSenhaCorpo2");
        this.emailEsqueceuSenhaRodape       = props.getProperty("emailEsqueceuSenhaRodape");
        
        //E-mail Marketing
        this.emailMarketingCabecalho        = props.getProperty("emailMarketingCabecalho");
    	this.emailMarketingCorpo1           = props.getProperty("emailMarketingCorpo1");
    	this.emailMarketingCorpo2           = props.getProperty("emailMarketingCorpo2");
    	this.emailMarketingRodape           = props.getProperty("emailMarketingRodape");
    	
    	 //E-mail Perfil Convite
    	this.emailPerfilConviteCabecalho    = props.getProperty("emailPerfilConviteCabecalho");
    	this.emailPerfilConviteFoto_src     = props.getProperty("emailPerfilConviteFoto_src");
    	this.emailPerfilConviteFoto_alt     = props.getProperty("emailPerfilConviteFoto_alt");
    	this.emailPerfilConviteFoto_title   = props.getProperty("emailPerfilConviteFoto_title");
    	this.emailPerfilConviteFoto_div     = props.getProperty("emailPerfilConviteFoto_div");
    	this.emailPerfilConviteCorpo1       = props.getProperty("emailPerfilConviteCorpo1");
    	this.emailPerfilConviteCorpo2       = props.getProperty("emailPerfilConviteCorpo2"); 
    	this.emailPerfilConviteCorpo3       = props.getProperty("emailPerfilConviteCorpo3");
    	this.emailPerfilConviteCorpo4       = props.getProperty("emailPerfilConviteCorpo4");
    	this.emailPerfilConviteRodape       = props.getProperty("emailPerfilConviteRodape");
    	
    	//E-mail Add Amigo
    	this.emailPerfilAddAmigoCabecalho   = props.getProperty("emailPerfilAddAmigoCabecalho");
    	this.emailPerfilAddAmigoCorpo1      = props.getProperty("emailPerfilAddAmigoCorpo1");
    	this.emailPerfilAddAmigoCorpo2      = props.getProperty("emailPerfilAddAmigoCorpo2");
    	this.emailPerfilAddAmigoFoto_src    = props.getProperty("emailPerfilAddAmigoFoto_src");
    	this.emailPerfilAddAmigoFoto_alt    = props.getProperty("emailPerfilAddAmigoFoto_alt");
    	this.emailPerfilAddAmigoFoto_title  = props.getProperty("emailPerfilAddAmigoFoto_title");
    	this.emailPerfilAddAmigoFoto_div    = props.getProperty("emailPerfilAddAmigoFoto_div");
    	this.emailPerfilAddAmigoCorpo3      = props.getProperty("emailPerfilAddAmigoCorpo3");
    	this.emailPerfilAddAmigoCorpo4      = props.getProperty("emailPerfilAddAmigoCorpo4");
    	this.emailPerfilAddAmigoCorpo5      = props.getProperty("emailPerfilAddAmigoCorpo5");
    	this.emailPerfilAddAmigoCorpo6      = props.getProperty("emailPerfilAddAmigoCorpo6");
    	this.emailPerfilAddAmigoCorpo7      = props.getProperty("emailPerfilAddAmigoCorpo7");
    	this.emailPerfilAddAmigoCorpo8     = props.getProperty("emailPerfilAddAmigoCorpo8");
    	this.emailPerfilAddAmigoCorpo9     = props.getProperty("emailPerfilAddAmigoCorpo9");
    	this.emailPerfilAddAmigoRodape      = props.getProperty("emailPerfilAddAmigoRodape");
    	
    	//E-mail Confirmacao Add Amigo
    	this.emailPerfilConfAddAmigoCabecalho = props.getProperty("emailPerfilConfAddAmigoCabecalho");
    	this.emailPerfilConfAddAmigoCorpo1 = props.getProperty("emailPerfilConfAddAmigoCorpo1");
    	this.emailPerfilConfAddAmigoCorpo2 = props.getProperty("emailPerfilConfAddAmigoCorpo2");
    	this.emailPerfilConfAddAmigoFoto_src = props.getProperty("emailPerfilConfAddAmigoFoto_src");
    	this.emailPerfilConfAddAmigoFoto_alt = props.getProperty("emailPerfilConfAddAmigoFoto_alt");
    	this.emailPerfilConfAddAmigoFoto_title = props.getProperty("emailPerfilConfAddAmigoFoto_title");
    	this.emailPerfilConfAddAmigoFoto_div = props.getProperty("emailPerfilConfAddAmigoFoto_div");
    	this.emailPerfilConfAddAmigoCorpo3 = props.getProperty("emailPerfilConfAddAmigoCorpo3");
    	this.emailPerfilConfAddAmigoCorpo4 = props.getProperty("emailPerfilConfAddAmigoCorpo4");
    	this.emailPerfilConfAddAmigoCorpo5 = props.getProperty("emailPerfilConfAddAmigoCorpo5");
    	this.emailPerfilConfAddAmigoCorpo6 = props.getProperty("emailPerfilConfAddAmigoCorpo6");
    	this.emailPerfilConfAddAmigoCorpo7 = props.getProperty("emailPerfilConfAddAmigoCorpo7");
    	this.emailPerfilConfAddAmigoCorpo8 = props.getProperty("emailPerfilConfAddAmigoCorpo8");
    	this.emailPerfilConfAddAmigoCorpo9 = props.getProperty("emailPerfilConfAddAmigoCorpo9");
    	this.emailPerfilConfAddAmigoRodape = props.getProperty("emailPerfilConfAddAmigoRodape");
    	
    	//E-mail Contato
    	this.emailContato       = props.getProperty("emailContato");
    	this.emailContatoRodape = props.getProperty("emailContatoRodape");
    	
    	//E-mail Anuncio
    	this.emailAnuncio       = props.getProperty("emailAnuncio");
    	this.emailAnuncioRodape = props.getProperty("emailAnuncioRodape");
		
	}

	public String getEmailPerfilAddAmigoCabecalho() {
		return emailPerfilAddAmigoCabecalho;
	}

	public String getEmailNewUserCabecalho() {
		return emailNewUserCabecalho;
	}

	public String getEmailNewUserCorpo() {
		return emailNewUserCorpo;
	}

	public String getEmailNewUserRodape() {
		return emailNewUserRodape;
	}

	public String getEmailEsqueceuSenhaCabecalho() {
		return emailEsqueceuSenhaCabecalho;
	}

	public String getEmailEsqueceuSenhaCorpo1() {
		return emailEsqueceuSenhaCorpo1;
	}

	public String getEmailEsqueceuSenhaCorpo2() {
		return emailEsqueceuSenhaCorpo2;
	}

	public String getEmailEsqueceuSenhaRodape() {
		return emailEsqueceuSenhaRodape;
	}

	public String getEmailMarketingCabecalho() {
		return emailMarketingCabecalho;
	}

	public String getEmailMarketingCorpo1() {
		return emailMarketingCorpo1;
	}

	public String getEmailMarketingCorpo2() {
		return emailMarketingCorpo2;
	}

	public String getEmailMarketingRodape() {
		return emailMarketingRodape;
	}

	public String getEmailPerfilConviteCabecalho() {
		return emailPerfilConviteCabecalho;
	}

	public String getEmailPerfilConviteCorpo1() {
		return emailPerfilConviteCorpo1;
	}

	public String getEmailPerfilConviteFoto_src() {
		return emailPerfilConviteFoto_src;
	}

	public String getEmailPerfilConviteFoto_alt() {
		return emailPerfilConviteFoto_alt;
	}

	public String getEmailPerfilConviteFoto_title() {
		return emailPerfilConviteFoto_title;
	}

	public String getEmailPerfilConviteFoto_div() {
		return emailPerfilConviteFoto_div;
	}

	public String getEmailPerfilConviteCorpo2() {
		return emailPerfilConviteCorpo2;
	}

	public String getEmailPerfilConviteCorpo3() {
		return emailPerfilConviteCorpo3;
	}

	public String getEmailPerfilConviteCorpo4() {
		return emailPerfilConviteCorpo4;
	}

	public String getEmailPerfilConviteRodape() {
		return emailPerfilConviteRodape;
	}

	public String getEmailPerfilAddAmigoCabacalho() {
		return emailPerfilAddAmigoCabacalho;
	}

	public String getEmailPerfilAddAmigoCorpo1() {
		return emailPerfilAddAmigoCorpo1;
	}

	public String getEmailPerfilAddAmigoCorpo2() {
		return emailPerfilAddAmigoCorpo2;
	}

	public String getEmailPerfilAddAmigoFoto_src() {
		return emailPerfilAddAmigoFoto_src;
	}

	public String getEmailPerfilAddAmigoFoto_alt() {
		return emailPerfilAddAmigoFoto_alt;
	}

	public String getEmailPerfilAddAmigoFoto_title() {
		return emailPerfilAddAmigoFoto_title;
	}

	public String getEmailPerfilAddAmigoFoto_div() {
		return emailPerfilAddAmigoFoto_div;
	}

	public String getEmailPerfilAddAmigoCorpo3() {
		return emailPerfilAddAmigoCorpo3;
	}

	public String getEmailPerfilAddAmigoCorpo4() {
		return emailPerfilAddAmigoCorpo4;
	}

	public String getEmailPerfilAddAmigoCorpo5() {
		return emailPerfilAddAmigoCorpo5;
	}

	public String getEmailPerfilAddAmigoCorpo6() {
		return emailPerfilAddAmigoCorpo6;
	}

	public String getEmailPerfilAddAmigoCorpo7() {
		return emailPerfilAddAmigoCorpo7;
	}

	public String getEmailPerfilAddAmigoCorpo8() {
		return emailPerfilAddAmigoCorpo8;
	}

	public String getEmailPerfilAddAmigoCorpo9() {
		return emailPerfilAddAmigoCorpo9;
	}

	public String getEmailPerfilAddAmigoRodape() {
		return emailPerfilAddAmigoRodape;
	}

	public String getEmailPerfilConfAddAmigoCabecalho() {
		return emailPerfilConfAddAmigoCabecalho;
	}

	public String getEmailPerfilConfAddAmigoCorpo1() {
		return emailPerfilConfAddAmigoCorpo1;
	}

	public String getEmailPerfilConfAddAmigoCorpo2() {
		return emailPerfilConfAddAmigoCorpo2;
	}

	public String getEmailPerfilConfAddAmigoFoto_src() {
		return emailPerfilConfAddAmigoFoto_src;
	}

	public String getEmailPerfilConfAddAmigoFoto_alt() {
		return emailPerfilConfAddAmigoFoto_alt;
	}

	public String getEmailPerfilConfAddAmigoFoto_title() {
		return emailPerfilConfAddAmigoFoto_title;
	}

	public String getEmailPerfilConfAddAmigoFoto_div() {
		return emailPerfilConfAddAmigoFoto_div;
	}

	public String getEmailPerfilConfAddAmigoCorpo3() {
		return emailPerfilConfAddAmigoCorpo3;
	}

	public String getEmailPerfilConfAddAmigoCorpo4() {
		return emailPerfilConfAddAmigoCorpo4;
	}

	public String getEmailPerfilConfAddAmigoCorpo5() {
		return emailPerfilConfAddAmigoCorpo5;
	}

	public String getEmailPerfilConfAddAmigoCorpo6() {
		return emailPerfilConfAddAmigoCorpo6;
	}

	public String getEmailPerfilConfAddAmigoCorpo7() {
		return emailPerfilConfAddAmigoCorpo7;
	}

	public String getEmailPerfilConfAddAmigoCorpo8() {
		return emailPerfilConfAddAmigoCorpo8;
	}

	public String getEmailPerfilConfAddAmigoCorpo9() {
		return emailPerfilConfAddAmigoCorpo9;
	}

	public String getEmailPerfilConfAddAmigoRodape() {
		return emailPerfilConfAddAmigoRodape;
	}

	public String getEmailContato() {
		return emailContato;
	}

	public String getEmailContatoRodape() {
		return emailContatoRodape;
	}

	public String getEmailAnuncio() {
		return emailAnuncio;
	}

	public String getEmailAnuncioRodape() {
		return emailAnuncioRodape;
	}
	
	
	
}
