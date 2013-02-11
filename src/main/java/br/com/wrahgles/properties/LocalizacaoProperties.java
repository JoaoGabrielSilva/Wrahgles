package br.com.wrahgles.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class LocalizacaoProperties {
	private String arquivo_invalido;
	private String imagem_aprovacao;
	private String localizacao_cadastrada;
	private String localizacao_atualizada;
	private String campo_nome_obrigatorio;
	private String categoria_subcategoria;
	private String campo_endereco_obrigatorio;
	private String selecione_estado;
	private String campo_cidade_obrigatorio;
	private String email_valido;
	private String campo_descricao_obrigatorio;
	private String endereco_mapa;
	
	public LocalizacaoProperties() throws IOException {
		
		Properties props = new Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream("conf/messages_class.properties");
		props.load(in);
		in.close();
		
		
		//LocalizacoaController
		this.arquivo_invalido           = props.getProperty("arquivo_invalido");
		this.imagem_aprovacao           = props.getProperty("imagem_aprovacao");
		this.localizacao_cadastrada     = props.getProperty("localizacao_cadastrada");
		this.localizacao_atualizada     = props.getProperty("localizacao_atualizada");
		this.campo_nome_obrigatorio     = props.getProperty("campo_nome_obrigatorio");
		this.categoria_subcategoria     = props.getProperty("categoria_subcategoria");
		this.campo_endereco_obrigatorio = props.getProperty("campo_endereco_obrigatorio");
		this.selecione_estado           = props.getProperty("selecione_estado");
		this.campo_cidade_obrigatorio   = props.getProperty("campo_cidade_obrigatorio");
		this.email_valido               = props.getProperty("email_valido");
		this.campo_descricao_obrigatorio= props.getProperty("campo_descricao_obrigatorio");
		this.endereco_mapa              = props.getProperty("endereco_mapa");
		
		
	}

	public String getArquivo_invalido() {
		return arquivo_invalido;
	}

	public String getImagem_aprovacao() {
		return imagem_aprovacao;
	}

	public String getLocalizacao_cadastrada() {
		return localizacao_cadastrada;
	}

	public String getLocalizacao_atualizada() {
		return localizacao_atualizada;
	}

	public String getCampo_nome_obrigatorio() {
		return campo_nome_obrigatorio;
	}

	public String getCategoria_subcategoria() {
		return categoria_subcategoria;
	}

	public String getCampo_endereco_obrigatorio() {
		return campo_endereco_obrigatorio;
	}

	public String getSelecione_estado() {
		return selecione_estado;
	}

	public String getCampo_cidade_obrigatorio() {
		return campo_cidade_obrigatorio;
	}

	public String getEmail_valido() {
		return email_valido;
	}

	public String getCampo_descricao_obrigatorio() {
		return campo_descricao_obrigatorio;
	}

	public String getEndereco_mapa() {
		return endereco_mapa;
	}
	
	
	

}
