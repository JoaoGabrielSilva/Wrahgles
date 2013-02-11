package br.com.wrahgles.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.wrahgles.util.DataUtil;
import br.com.wrahgles.util.TextoUtil;

@Entity
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String senha ;
	
	@Column(nullable = false, length = 5000)
	private String fotoPerfil;
	
	@Transient
	private String senhaTemp;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtCadastro;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtUltimoUpdate;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
    private Date dtUltimoAcesso;
	
	@Column(nullable = false)
	private int qtdAcesso;
	
	@Column(nullable = false)
	private boolean flgInfoWrahgles;
	
	@Column(nullable = false)
	private boolean flgAtivo;
	
	@Column(nullable = false)
	private String role;
	
	public Usuario() {}
	
	public String getNomeFormat(){
		return new TextoUtil().retirarAcentosECaracteresEspeciais(nome);
		
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getSenhaTemp() {
		return senhaTemp;
	}

	public void setSenhaTemp(String senhaTemp) {
		this.senhaTemp = senhaTemp;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	
	public String getDataCadastroFormat(){
		return DataUtil.formatarDataTimestamp(dtCadastro);
	}

	public Date getDtUltimoUpdate() {
		return dtUltimoUpdate;
	}

	public void setDtUltimoUpdate(Date dtUltimoUpdate) {
		this.dtUltimoUpdate = dtUltimoUpdate;
	}

	public Date getDtUltimoAcesso() {
		return dtUltimoAcesso;
	}

	public void setDtUltimoAcesso(Date dtUltimoAcesso) {
		this.dtUltimoAcesso = dtUltimoAcesso;
	}

	public int getQtdAcesso() {
		return qtdAcesso;
	}

	public void setQtdAcesso(int qtdAcesso) {
		this.qtdAcesso = qtdAcesso;
	}

	public boolean isFlgInfoWrahgles() {
		return flgInfoWrahgles;
	}

	public void setFlgInfoWrahgles(boolean flgInfoWrahgles) {
		this.flgInfoWrahgles = flgInfoWrahgles;
	}

	public boolean isFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
	

}
