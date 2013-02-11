package br.com.wrahgles.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.wrahgles.util.DataUtil;

@Entity
public class LocalizacaoFoto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="ID_LOCALIZACAO", nullable = false)
	private Localizacao localizacao;
	
	@ManyToOne
	@JoinColumn(name="ID_USUARIO", nullable = false)
	private Usuario usuario;
	
	@Column(nullable = false)
	private String imagem;
	
	@Column(nullable = false)
	private boolean flgAtivo;
	
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;
	
	public LocalizacaoFoto() {}

	public String getDataFormat(){
		return DataUtil.formatarDataTimestamp(data);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public boolean isFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	
}
