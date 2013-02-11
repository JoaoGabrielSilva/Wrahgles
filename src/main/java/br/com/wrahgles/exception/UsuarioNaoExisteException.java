package br.com.wrahgles.exception;

public class UsuarioNaoExisteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public UsuarioNaoExisteException(){
		super();
	}
	
	
	public UsuarioNaoExisteException(String msg, Exception ex){
		super(msg, ex);
	}
	
	public UsuarioNaoExisteException(String msg){
		super(msg);
	}
	
	public UsuarioNaoExisteException(Exception ex){
		super(ex);
	}
	

}
