package com.wallmart.model.vo;

import java.util.List;

public class DeliveryVO {
	
	private List<PointVO> pontos;

	//FIXME BIGDECIMAL
	private Double custo;
	
	public DeliveryVO() {
		super();
	}
	
	public DeliveryVO(Double custo, List<PointVO> pontos) {
		this.custo = custo;
		this.pontos = pontos;
	}
	
	public List<PointVO> getPoints() {
		return pontos;
	}
	public void setPontos(List<PointVO> pontos) {
		this.pontos = pontos;
	}
	public Double getCost() {
		return custo;
	}
	public void setCusto(Double custo) {
		this.custo = custo;
	}

}
