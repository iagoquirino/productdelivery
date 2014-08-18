package com.wallmart.exception;

import org.springframework.http.HttpStatus;

import com.wallmart.rest.json.MensagemJSON;

public class APIException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8521620839846364523L;

	private HttpStatus httpStatus;

	private MensagemJSON mensagemJSON;

    private String mensagem;
    
    public APIException( String mensagem , HttpStatus httpStatus)
    {
        super( mensagem );
        this.mensagem = mensagem ;
        this.httpStatus = httpStatus;
        this.mensagemJSON = new MensagemJSON(this.mensagem);
    }
    
    public MensagemJSON getMensagemJSON() {
		return mensagemJSON;
	}
    
    public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
