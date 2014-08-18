package com.wallmart.rest.json;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RotaJSON {
	
	private String origem;
	
	private String destino;
	
	//FIXME BIGDECIMAL
	private Integer distancia;
	
	private Integer autonomia;
	
	private Double valorCombustivel;
	
	public RotaJSON() {
		super();
	}
	
	public RotaJSON(String origem, String destino, Integer distancia) {
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
	}
	
	public RotaJSON(String origem, String destino, Integer autonomia, Double valorCombustivel) {
		this.origem = origem;
		this.destino = destino;
		this.autonomia = autonomia;
		this.valorCombustivel = valorCombustivel;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	public Integer getAutonomia() {
		return autonomia;
	}

	public void setAutonomia(Integer autonomia) {
		this.autonomia = autonomia;
	}

	public Double getValorCombustivel() {
		return valorCombustivel;
	}

	public void setValorCombustivel(Double valorCombustivel) {
		this.valorCombustivel = valorCombustivel;
	}
	
}
