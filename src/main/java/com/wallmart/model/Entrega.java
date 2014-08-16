package com.wallmart.model;

import java.util.List;

public class Entrega {
	
	private List<Ponto> pontos;

	//FIXME BIGDECIMAL
	private Double custo;
	
	public Entrega(Double custo, List<Ponto> pontos) {
		this.custo = custo;
		this.pontos = pontos;
	}
	public List<Ponto> getPontos() {
		return pontos;
	}
	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}
	public Double getCusto() {
		return custo;
	}
	public void setCusto(Double custo) {
		this.custo = custo;
	}

}
