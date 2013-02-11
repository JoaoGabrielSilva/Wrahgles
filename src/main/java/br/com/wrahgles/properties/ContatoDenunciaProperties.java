package br.com.wrahgles.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ContatoDenunciaProperties {
	private String denuncia_localizacao;
	private String denuncia;
	private String contato;
	private String equipe_contato;
	private String campo_nome_obrigatorio;
	private String campo_email_obrigatorio;
	private String email_valido;
	private String campo_mensagem_obrigatorio;
	private String patrocinado;
	
	public ContatoDenunciaProperties() throws IOException{
		
		
		Properties props = new Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream("conf/messages_class.properties");
		props.load(in);
		in.close();
		
		
		this.denuncia_localizacao        = props.getProperty("denuncia_localizacao");
		this.denuncia                    = props.getProperty("denuncia");
		this.contato                     = props.getProperty("contato");
		this.equipe_contato              = props.getProperty("equipe_contato");  
		this.campo_nome_obrigatorio      = props.getProperty("campo_nome_obrigatorio");
		this.campo_email_obrigatorio     = props.getProperty("campo_email_obrigatorio");
		this.email_valido                = props.getProperty("email_valido");
		this.campo_mensagem_obrigatorio  = props.getProperty("campo_mensagem_obrigatorio");
		this.patrocinado                 = props.getProperty("patrocinado");
	}

	
	public String getDenuncia_localizacao() {
		return denuncia_localizacao;
	}

	public void setDenuncia_localizacao(String denuncia_localizacao) {
		this.denuncia_localizacao = denuncia_localizacao;
	}

	public String getDenuncia() {
		return denuncia;
	}

	public void setDenuncia(String denuncia) {
		this.denuncia = denuncia;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getEquipe_contato() {
		return equipe_contato;
	}

	public void setEquipe_contato(String equipe_contato) {
		this.equipe_contato = equipe_contato;
	}

	public String getCampo_nome_obrigatorio() {
		return campo_nome_obrigatorio;
	}

	public void setCampo_nome_obrigatorio(String campo_nome_obrigatorio) {
		this.campo_nome_obrigatorio = campo_nome_obrigatorio;
	}

	public String getCampo_email_obrigatorio() {
		return campo_email_obrigatorio;
	}

	public void setCampo_email_obrigatorio(String campo_email_obrigatorio) {
		this.campo_email_obrigatorio = campo_email_obrigatorio;
	}

	public String getEmail_valido() {
		return email_valido;
	}

	public void setEmail_valido(String email_valido) {
		this.email_valido = email_valido;
	}

	public String getCampo_mensagem_obrigatorio() {
		return campo_mensagem_obrigatorio;
	}

	public void setCampo_mensagem_obrigatorio(String campo_mensagem_obrigatorio) {
		this.campo_mensagem_obrigatorio = campo_mensagem_obrigatorio;
	}

	public String getPatrocinado() {
		return patrocinado;
	}

	public void setPatrocinado(String patrocinado) {
		this.patrocinado = patrocinado;
	}
	
	

}
