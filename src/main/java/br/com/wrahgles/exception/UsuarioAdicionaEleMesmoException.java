package br.com.wrahgles.exception;

public class UsuarioAdicionaEleMesmoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsuarioAdicionaEleMesmoException(){
		super();
	}
	
	public UsuarioAdicionaEleMesmoException(String msgm,Exception ex){
		super(msgm, ex);
	}
	
	public UsuarioAdicionaEleMesmoException(String msg){
		super(msg);
	}

	
	public UsuarioAdicionaEleMesmoException(Exception ex){
		super(ex);
	}
	
}

