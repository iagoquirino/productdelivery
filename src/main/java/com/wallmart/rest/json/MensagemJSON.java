package com.wallmart.rest.json;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class MensagemJSON {
	
	private Long codigo;
	
	private String mensagem;
	
	public MensagemJSON() {
		super();
	}
	
	public MensagemJSON(String mensagem) {
		this.mensagem = mensagem;
	}

	public MensagemJSON(Long codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
