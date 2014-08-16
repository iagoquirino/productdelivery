package com.wallmart.model.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class MapaJSON {
	
	private Long id;
	
	private String nome;
	
	private List<RotaJSON> rotas = new ArrayList<RotaJSON>();
	
	public MapaJSON() {
		super();
	}
	
	public MapaJSON(String nome) {
		this.nome = nome;
	}
	
	public MapaJSON(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<RotaJSON> getRotas() {
		return rotas;
	}
	
	public void setRotas(List<RotaJSON> rotas) {
		this.rotas = rotas;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
