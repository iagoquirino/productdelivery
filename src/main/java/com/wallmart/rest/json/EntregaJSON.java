package com.wallmart.rest.json;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class EntregaJSON {
	
	private List<PontoJSON> pontos;
	//FIXME BIGDECIMAL
	private Double custo;
	
	public List<PontoJSON> getPontos() {
		return pontos;
	}
	public void setPontos(List<PontoJSON> pontos) {
		this.pontos = pontos;
	}
	public Double getCusto() {
		return custo;
	}
	public void setCusto(Double custo) {
		this.custo = custo;
	}
}
