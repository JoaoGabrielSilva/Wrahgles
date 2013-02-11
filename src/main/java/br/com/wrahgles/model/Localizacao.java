package br.com.wrahgles.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.wrahgles.util.TextoUtil;

@Entity
public class Localizacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false, length = 250)
	private String descricao;
	@Column(nullable = false)
	private String endereco;
	@Column(nullable = false)
	private String complemento;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String site;
	@Column(nullable = false)
	private String telefone;
	@Column(nullable = false)
	private Double latitude;
	@Column(nullable = false)
	private Double longitude;
	@Column(nullable = false)
	private boolean flgAtivo;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtCadastro;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtUltimoUpdate;
	
	@ManyToOne
	@JoinColumn(name = "ID_ESTADO", nullable = false)
	private Estado estado;
	@Column(nullable = false, length = 150)
	private String cidade;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO_CADASTRO", nullable = false)
	private Usuario usuarioInsert;
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO_ALTERA", nullable = false)
	private Usuario usuarioUpdate;
	
	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA",nullable = false)
	private RelCategoria categoria;
	
	@OneToMany(mappedBy = "localizacao", cascade = CascadeType.ALL)
	private List<Comentario> comentarios;
	
	@OneToMany(mappedBy = "localizacao", cascade = CascadeType.ALL)
	private List<Denuncia> denuncias;
	
	public Localizacao(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public boolean isFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Date getDtUltimoUpdate() {
		return dtUltimoUpdate;
	}

	public void setDtUltimoUpdate(Date dtUltimoUpdate) {
		this.dtUltimoUpdate = dtUltimoUpdate;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Usuario getUsuarioInsert() {
		return usuarioInsert;
	}

	public void setUsuarioInsert(Usuario usuarioInsert) {
		this.usuarioInsert = usuarioInsert;
	}

	public Usuario getUsuarioUpdate() {
		return usuarioUpdate;
	}

	public void setUsuarioUpdate(Usuario usuarioUpdate) {
		this.usuarioUpdate = usuarioUpdate;
	}

	public RelCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(RelCategoria categoria) {
		this.categoria = categoria;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentario(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Denuncia> getDenuncias() {
		return denuncias;
	}

	public void setDenuncias(List<Denuncia> denuncias) {
		this.denuncias = denuncias;
	}
	
	public String getNomeFormat(){
		return new TextoUtil().retirarAcentosECaracteresEspeciais(nome);
	}
	
	
	
	
	
	
	
	

}
