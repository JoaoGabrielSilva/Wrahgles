package br.com.wrahgles.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class AdminProperties {
	private String cadastro_subcategoria;
	private String atualizado_subcatoria;
	private String excluir_subcategoria;
	private String campo_nome_obrigatorio;
	private String subcateoria_sistema;
	private String campo_subcategoria_obrigatorio;
	private String subcategoria_nao_encontrada;
	private String subcategoria_relacionada;
	private String campo_descricao_obrigatorio;
	private String notificacao_enviado;
	private String convite_enviado;
	private String campo_titulo_obrigatorio;
	private String campo_mensagem_obrigatorio;
	private String selecionar;
	private String comentario_excluido;
	private String comentario_aprovado;
	private String categoria_cadastrada;
	private String categoria_atualizada;
	private String categoria_excluida;
	private String categoria_sistema;
	
	
	public AdminProperties() throws IOException{
		
		Properties props = new Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream("conf/messages_class.properties");
		props.load(in);
		in.close();
		
		this.cadastro_subcategoria          = props.getProperty("cadastro_subcategoria");
		this.atualizado_subcatoria          = props.getProperty("atualizado_subcategoria");
		this.excluir_subcategoria           = props.getProperty("excluir_subcategoria");
		this.campo_nome_obrigatorio         = props.getProperty("campo_nome_obrigatorio");
		this.subcateoria_sistema            = props.getProperty("subcategoria_sistema");
		this.campo_subcategoria_obrigatorio = props.getProperty("campo_subcategoria_obrigatorio");
		this.subcategoria_nao_encontrada    = props.getProperty("campo_subcategoria_obrigatorio");
		this.subcategoria_relacionada       = props.getProperty("subcategoria_relacionada");
		this.campo_descricao_obrigatorio    = props.getProperty("campo_descricao_obrigatorio");
		this.notificacao_enviado            = props.getProperty("notificacao_enviado");
		this.convite_enviado                = props.getProperty("convite_enviado");
		this.campo_titulo_obrigatorio        = props.getProperty("campo_titulo_obrigatorio");
		this.campo_mensagem_obrigatorio     = props.getProperty("campo_mensagem_obrigatorio");
		this.selecionar                     = props.getProperty("selecionar");
		this.comentario_aprovado            = props.getProperty("comentario_aprovado");
		this.comentario_excluido            = props.getProperty("comentario_excluido");
		this.categoria_atualizada           = props.getProperty("categoria_atualizada");
		this.categoria_cadastrada           = props.getProperty("categoria_cadastrada");
		this.categoria_excluida             = props.getProperty("categoria_excluida");
		this.categoria_sistema              = props.getProperty("categoria_sistema");
		
	}


	public String getCadastro_subcategoria() {
		return cadastro_subcategoria;
	}


	public void setCadastro_subcategoria(String cadastro_subcategoria) {
		this.cadastro_subcategoria = cadastro_subcategoria;
	}


	public String getAtualizado_subcatoria() {
		return atualizado_subcatoria;
	}


	public void setAtualizado_subcatoria(String atualizado_subcatoria) {
		this.atualizado_subcatoria = atualizado_subcatoria;
	}


	public String getExcluir_subcategoria() {
		return excluir_subcategoria;
	}


	public void setExcluir_subcategoria(String excluir_subcategoria) {
		this.excluir_subcategoria = excluir_subcategoria;
	}


	public String getCampo_nome_obrigatorio() {
		return campo_nome_obrigatorio;
	}


	public void setCampo_nome_obrigatorio(String campo_nome_obrigatorio) {
		this.campo_nome_obrigatorio = campo_nome_obrigatorio;
	}


	public String getSubcateoria_sistema() {
		return subcateoria_sistema;
	}


	public void setSubcateoria_sistema(String subcateoria_sistema) {
		this.subcateoria_sistema = subcateoria_sistema;
	}


	public String getCampo_subcategoria_obrigatorio() {
		return campo_subcategoria_obrigatorio;
	}


	public void setCampo_subcategoria_obrigatorio(
			String campo_subcategoria_obrigatorio) {
		this.campo_subcategoria_obrigatorio = campo_subcategoria_obrigatorio;
	}


	public String getSubcategoria_nao_encontrada() {
		return subcategoria_nao_encontrada;
	}


	public void setSubcategoria_nao_encontrada(String subcategoria_nao_encontrada) {
		this.subcategoria_nao_encontrada = subcategoria_nao_encontrada;
	}


	public String getSubcategoria_relacionada() {
		return subcategoria_relacionada;
	}


	public void setSubcategoria_relacionada(String subcategoria_relacionada) {
		this.subcategoria_relacionada = subcategoria_relacionada;
	}


	public String getCampo_descricao_obrigatorio() {
		return campo_descricao_obrigatorio;
	}


	public void setCampo_descricao_obrigatorio(String campo_descricao_obrigatorio) {
		this.campo_descricao_obrigatorio = campo_descricao_obrigatorio;
	}


	public String getNotificacao_enviado() {
		return notificacao_enviado;
	}


	public void setNotificacao_enviado(String notificacao_enviado) {
		this.notificacao_enviado = notificacao_enviado;
	}


	public String getConvite_enviado() {
		return convite_enviado;
	}


	public void setConvite_enviado(String convite_enviado) {
		this.convite_enviado = convite_enviado;
	}


	public String getCampo_titulo_obrigatorio() {
		return campo_titulo_obrigatorio;
	}


	public void setCampo_titulo_obrigatorio(String campo_titulo_obrigatorio) {
		this.campo_titulo_obrigatorio = campo_titulo_obrigatorio;
	}


	public String getCampo_mensagem_obrigatorio() {
		return campo_mensagem_obrigatorio;
	}


	public void setCampo_mensagem_obrigatorio(String campo_mensagem_obrigatorio) {
		this.campo_mensagem_obrigatorio = campo_mensagem_obrigatorio;
	}


	public String getSelecionar() {
		return selecionar;
	}


	public void setSelecionar(String selecionar) {
		this.selecionar = selecionar;
	}


	public String getComentario_excluido() {
		return comentario_excluido;
	}


	public void setComentario_excluido(String comentario_excluido) {
		this.comentario_excluido = comentario_excluido;
	}


	public String getComentario_aprovado() {
		return comentario_aprovado;
	}


	public void setComentario_aprovado(String comentario_aprovado) {
		this.comentario_aprovado = comentario_aprovado;
	}


	public String getCategoria_cadastrada() {
		return categoria_cadastrada;
	}


	public void setCategoria_cadastrada(String categoria_cadastrada) {
		this.categoria_cadastrada = categoria_cadastrada;
	}


	public String getCategoria_atualizada() {
		return categoria_atualizada;
	}


	public void setCategoria_atualizada(String categoria_atualizada) {
		this.categoria_atualizada = categoria_atualizada;
	}


	public String getCategoria_excluida() {
		return categoria_excluida;
	}


	public void setCategoria_excluida(String categoria_excluida) {
		this.categoria_excluida = categoria_excluida;
	}


	public String getCategoria_sistema() {
		return categoria_sistema;
	}


	public void setCategoria_sistema(String categoria_sistema) {
		this.categoria_sistema = categoria_sistema;
	}
	
}
