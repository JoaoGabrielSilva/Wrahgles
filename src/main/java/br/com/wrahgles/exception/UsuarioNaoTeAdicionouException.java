package br.com.wrahgles.exception;

public class UsuarioNaoTeAdicionouException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoTeAdicionouException(){
		super();	}
	
	public UsuarioNaoTeAdicionouException(String msg, Exception ex){
		super (msg, ex); }
	
	public UsuarioNaoTeAdicionouException(String msg){
		super(msg); }
	
	public UsuarioNaoTeAdicionouException(Exception ex){
		super (ex); }
	}


