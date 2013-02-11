package br.com.wrahgles.dao;

import java.util.List;

import br.com.wrahgles.model.UsuarioAmigo;
import br.com.wrahgles.model.UsuarioAmigoPK;

public interface UsuarioAmigoDAO{

	public void saveOrUpdate(UsuarioAmigo usuarioAmigo);
	public void save(UsuarioAmigo usuarioAmigo);
	public void update(UsuarioAmigo usuarioAmigo);
	public void delete(UsuarioAmigo usuarioAmigo);
	public List<UsuarioAmigo> listAmigos(Long id, boolean flgConfirmacao);
	public List<UsuarioAmigo> listSolicitacoesPendentes(Long id);
	public int countSolicitacoesPendentes(Long id);
	public List<UsuarioAmigo> listAmigos(Long id, boolean flgConfirma, int qtdResult);
	public int countAmigos(Long id, boolean flgConfirma);
	public UsuarioAmigo findById(UsuarioAmigoPK id);
	
}
