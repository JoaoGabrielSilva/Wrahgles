package br.com.wrahgles.exception;

public class UsuarioJaFoiAdicionadoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public UsuarioJaFoiAdicionadoException(){
		super();
	}
	
	public UsuarioJaFoiAdicionadoException(String msg, Exception ex){
		super(msg,ex);
	}
	
	public UsuarioJaFoiAdicionadoException(String msg){
		super(msg);
	}
	
	public UsuarioJaFoiAdicionadoException(Exception ex){
		super(ex);
	}

}
