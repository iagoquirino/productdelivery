package com.wallmart.model.vo;

import java.util.List;

public class EntregaVO {
	
	private List<PontoVO> pontos;

	//FIXME BIGDECIMAL
	private Double custo;
	
	public EntregaVO() {
		super();
	}
	
	public EntregaVO(Double custo, List<PontoVO> pontos) {
		this.custo = custo;
		this.pontos = pontos;
	}
	
	public List<PontoVO> getPontos() {
		return pontos;
	}
	public void setPontos(List<PontoVO> pontos) {
		this.pontos = pontos;
	}
	public Double getCusto() {
		return custo;
	}
	public void setCusto(Double custo) {
		this.custo = custo;
	}

}
