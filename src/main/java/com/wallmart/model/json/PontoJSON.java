package com.wallmart.model.json;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PontoJSON {
	
	private String nome;
	
	public PontoJSON() {
		super();
	}
	
	public PontoJSON(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
