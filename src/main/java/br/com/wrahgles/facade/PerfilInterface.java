package br.com.wrahgles.facade;

import br.com.wrahgles.exception.UsuarioAdicionaEleMesmoException;
import br.com.wrahgles.exception.UsuarioJaFoiAdicionadoException;
import br.com.wrahgles.exception.UsuarioNaoExisteException;
import br.com.wrahgles.exception.UsuarioNaoTeAdicionouException;
import br.com.wrahgles.exception.UsuarioDeveConfirmarAmigoException;
import br.com.wrahgles.model.Usuario;

public interface PerfilInterface {
	
	public void adicionarAmigo(Usuario usuarioLogado, Long idAmigo) throws UsuarioNaoExisteException,UsuarioAdicionaEleMesmoException,
	UsuarioJaFoiAdicionadoException, UsuarioDeveConfirmarAmigoException;
	
	public void confirmarAmigo(Usuario usuarioLogado, Long idAmigo)throws UsuarioNaoExisteException,UsuarioAdicionaEleMesmoException,
	UsuarioNaoTeAdicionouException, UsuarioJaFoiAdicionadoException;
	
	public void recusarAmigo(Usuario usuarioLogado, Long idAmigo);
	
	public void removerAmigo(Usuario usuarioLogado, Long idAmigo);
	

}
