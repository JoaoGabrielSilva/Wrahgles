package br.com.wrahgles.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class PerfilProperties {
	
	
	private String erro_add_voce_mesmo;
	private String erro_add_usuario;
	private String conf_amizade;
	private String usuario_nao_existe;
	private String erro_conf_voce_mesmo;
	private String erro_add_conf_amizade;
	private String erro_add_lista_amigo;
	private String erro_solicitacao;
	private String removido_lista_amigo;
	private String conf_locais;
	private String conf_frequencia;
	
	public PerfilProperties() throws IOException{
		
		
		Properties props = new Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream("conf/messages_class.properties");
		props.load(in);
		in.close();
		
		
		
		this.conf_amizade             = props.getProperty("conf_amizade");
		this.conf_frequencia          = props.getProperty("conf_frequencia");
		this.conf_locais              = props.getProperty("conf_locais");
		this.erro_add_conf_amizade    = props.getProperty("erro_add_conf_amizade");
		this.erro_add_lista_amigo     = props.getProperty("erro_add_lista_amigo");
		this.erro_add_usuario         = props.getProperty("erro_add_usuario");
		this.erro_add_voce_mesmo      = props.getProperty("erro_add_voce_mesmo");
		this.erro_conf_voce_mesmo     = props.getProperty("erro_conf_voce_mesmo");
		this.erro_solicitacao         = props.getProperty("erro_solicitacao");
		this.removido_lista_amigo     = props.getProperty("removido_lista_amigo");
		this.usuario_nao_existe       = props.getProperty("usuario_nao_existe");
		
		
		
	}

	public String getErro_add_voce_mesmo() {
		return erro_add_voce_mesmo;
	}

	public String getErro_add_usuario() {
		return erro_add_usuario;
	}

	public String getConf_amizade() {
		return conf_amizade;
	}

	public String getUsuario_nao_existe() {
		return usuario_nao_existe;
	}

	public String getErro_conf_voce_mesmo() {
		return erro_conf_voce_mesmo;
	}

	public String getErro_add_conf_amizade() {
		return erro_add_conf_amizade;
	}

	public String getErro_add_lista_amigo() {
		return erro_add_lista_amigo;
	}

	public String getErro_solicitacao() {
		return erro_solicitacao;
	}

	public String getRemovido_lista_amigo() {
		return removido_lista_amigo;
	}

	public String getConf_locais() {
		return conf_locais;
	}

	public String getConf_frequencia() {
		return conf_frequencia;
	}

	
	
}
