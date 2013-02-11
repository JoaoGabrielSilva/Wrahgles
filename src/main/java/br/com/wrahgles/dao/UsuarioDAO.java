package br.com.wrahgles.dao;

import java.util.List;

import br.com.wrahgles.bean.query.UsuarioQuery;
import br.com.wrahgles.model.Usuario;

public interface UsuarioDAO extends GenericoDAO<Usuario, Long>{
	
	public Usuario findByEmailAndSenha(Usuario usuario);
	public Usuario findByEmail(Usuario usuario);
	public Usuario findByEmail(String email);
	public List<Usuario> findByFlgInfoWrahgles(boolean valor);
	public List<Usuario> findByNomeOrEmailOrderDtUltimoAcesso(String str, int qtdResult, int page);
	public int countByNomeOrEmail(String str);
	public List<Usuario> findAllOrderDtCadastro(int qtdResult, int page);
	public List<UsuarioQuery> findWithMoreComentario(int qtdResult);
	

}
