package com.wallmart.exception;

public class ProductDeliveryException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -187500547480316378L;
	
	private String message;
	    
    public ProductDeliveryException( String message )
    {
    	super( message );
        this.message = message ;
    }
    
    public String getMessage() {
		return message;
	}

}
