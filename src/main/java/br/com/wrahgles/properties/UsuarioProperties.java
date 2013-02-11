package br.com.wrahgles.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.caelum.vraptor.ioc.Component;
@Component
public class UsuarioProperties {
	
	private String campo_email_obrigatorio;
	private String campo_senha_obrigatorio;
	private String usuario_senha_invalidos;
	private String bem_vindo;
	private String cadastro_wrahgles;
	private String dados_atualizados;
	private String receber_email_senha;
	private String email_nao_encontrado;
	private String senha_perdida;
	private String senha_enviada;
	private String arquivo_invalido;
	private String campo_nome_obrigatorio;
	private String email_valido;
	private String email_cadastrado;
	private String campo_senha_caracter;
	private String campo_confirmar;
	private String campo_senha_confirmar;
	private String termos_uso;
	

	public UsuarioProperties() throws IOException{
		
		Properties props = new Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream("conf/messages_class.properties");
	    props.load(in);
	    in.close();
	    
	    
	    this.arquivo_invalido     = props.getProperty("arquivo_invalido");
	    this.bem_vindo            = props.getProperty("bem_vindo");
	    this.cadastro_wrahgles    = props.getProperty("cadastro_wrahgles");
	    this.campo_confirmar      = props.getProperty("campo_confirmar");
	    this.campo_email_obrigatorio = props.getProperty("campo_email_obrigatorio");
	    this.campo_nome_obrigatorio  = props.getProperty("campo_nome_obrigatorio");
	    this.campo_senha_caracter    = props.getProperty("campo_senha_caracter");
	    this.campo_senha_confirmar   = props.getProperty("campo_senha_confirmar");
	    this.campo_senha_obrigatorio = props.getProperty("campo_senha_obrigatorio");
	    this.dados_atualizados       = props.getProperty("dados_atualizados");
	    this.email_cadastrado        = props.getProperty("email_cadastrado");
	    this.email_nao_encontrado    = props.getProperty("email_nao_encontrado");
	    this.email_valido            = props.getProperty("email_valido");
	    this.receber_email_senha     = props.getProperty("receber_email_senha");
	    this.senha_enviada           = props.getProperty("senha_enviada");
	    this.senha_perdida           = props.getProperty("senha_perdida");
	    this.termos_uso              = props.getProperty("termos_uso");
	    this.usuario_senha_invalidos = props.getProperty("usuario_senha_invalidos");
	    
		
	}


	public String getCampo_email_obrigatorio() {
		return campo_email_obrigatorio;
	}


	public String getCampo_senha_obrigatorio() {
		return campo_senha_obrigatorio;
	}


	public String getUsuario_senha_invalidos() {
		return usuario_senha_invalidos;
	}


	public String getBem_vindo() {
		return bem_vindo;
	}


	public String getCadastro_wrahgles() {
		return cadastro_wrahgles;
	}


	public String getDados_atualizados() {
		return dados_atualizados;
	}


	public String getReceber_email_senha() {
		return receber_email_senha;
	}


	public String getEmail_nao_encontrado() {
		return email_nao_encontrado;
	}


	public String getSenha_perdida() {
		return senha_perdida;
	}


	public String getSenha_enviada() {
		return senha_enviada;
	}


	public String getArquivo_invalido() {
		return arquivo_invalido;
	}


	public String getCampo_nome_obrigatorio() {
		return campo_nome_obrigatorio;
	}


	public String getEmail_valido() {
		return email_valido;
	}


	public String getEmail_cadastrado() {
		return email_cadastrado;
	}


	public String getCampo_senha_caracter() {
		return campo_senha_caracter;
	}


	public String getCampo_confirmar() {
		return campo_confirmar;
	}


	public String getCampo_senha_confirmar() {
		return campo_senha_confirmar;
	}


	public String getTermos_uso() {
		return termos_uso;
	}
	
	
	
}
