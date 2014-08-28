package com.wallmart.model.vo;

import java.util.ArrayList;
import java.util.List;

public class RoutesGroupVO {
	
	private int totalPercorrido;
	
	private List<PointVO> lugaresPercorridos;

	public RoutesGroupVO() {
		super();
	}
	
	public RoutesGroupVO(int totalPercorrido ) {
		this.totalPercorrido = totalPercorrido;
	}

	public int getTotalPercorrido() {
		return totalPercorrido;
	}
	
	public void setTotalPercorrido(int totalPercorrido) {
		this.totalPercorrido = totalPercorrido;
	}
	
	public List<PointVO> getLugaresPercorridos() {
		List<PointVO> pontos = new ArrayList<PointVO>();
		if(this.lugaresPercorridos != null){
			pontos = this.lugaresPercorridos;
		}
		return pontos;
	}
	
	public void setLugaresPercorridos(List<PointVO> lugaresPercorridos) {
		this.lugaresPercorridos = lugaresPercorridos;
	}
	
	public void addDistance(int valor)
	{
		this.totalPercorrido = this.totalPercorrido + valor;
	}
	
	public void adicionarLugar(String lugar){
		PointVO ponto = new PointVO(lugar);
		adicionarLugar(getLugaresPercorridos(), ponto);
	}

	private void adicionarLugar(List<PointVO> pontos, PointVO ponto) {
		if(!pontos.contains(ponto)){
			pontos.add(ponto);	
		}
		setLugaresPercorridos(pontos);
	}

	public void adicionarLugares(List<PointVO> lugares) {
		if(lugares != null){
			for (PointVO ponto : lugares) {
				adicionarLugar(getLugaresPercorridos(), ponto);
			}	
		}
	}
	
	
}
