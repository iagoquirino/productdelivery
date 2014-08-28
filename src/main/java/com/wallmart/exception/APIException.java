package com.wallmart.exception;

import org.springframework.http.HttpStatus;

import com.wallmart.rest.json.MessageJSON;

public class APIException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8521620839846364523L;

	private HttpStatus httpStatus;

	private MessageJSON messageJSON;

    private String message;
    
    public APIException( String message , HttpStatus httpStatus)
    {
        super( message );
        this.message = message ;
        this.httpStatus = httpStatus;
        this.messageJSON = new MessageJSON(this.message);
    }
    
    public MessageJSON getMessageJSON() {
		return messageJSON;
	}
    
    public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
