package br.com.wrahgles.exception;

public class UsuarioDeveConfirmarAmigoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public UsuarioDeveConfirmarAmigoException(){
		super();
	}
	
	public UsuarioDeveConfirmarAmigoException(String msg, Exception ex){
		super(msg,ex);
	}
	
	public UsuarioDeveConfirmarAmigoException(String msg){
		super(msg);
	}
	
	public UsuarioDeveConfirmarAmigoException(Exception ex){
		super(ex);
	}
	
	
	

}
