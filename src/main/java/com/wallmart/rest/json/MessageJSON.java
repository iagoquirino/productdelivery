package com.wallmart.rest.json;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class MessageJSON {
	
	private Long code;
	
	private String message;
	
	public MessageJSON() {
		super();
	}
	
	public MessageJSON(String message) {
		this.message = message;
	}

	public MessageJSON(Long code, String message) {
		this.code = code;
		this.message = message;
	}

	public Long getCode() {
		return code;
	}
	
	public void setCode(Long code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
