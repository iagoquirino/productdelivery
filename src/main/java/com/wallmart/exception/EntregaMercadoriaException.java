package com.wallmart.exception;

public class EntregaMercadoriaException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -187500547480316378L;
	
	private String mensagem;
	    
    public EntregaMercadoriaException( String mensagem )
    {
    	super( mensagem );
        this.mensagem = mensagem ;
    }
    
    public String getMensagem() {
		return mensagem;
	}

}
