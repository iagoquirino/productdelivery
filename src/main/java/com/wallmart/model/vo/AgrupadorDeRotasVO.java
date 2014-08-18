package com.wallmart.model.vo;

import java.util.ArrayList;
import java.util.List;

public class AgrupadorDeRotasVO {
	
	private int totalPercorrido;
	
	private List<PontoVO> lugaresPercorridos;

	public AgrupadorDeRotasVO() {
		super();
	}
	
	public AgrupadorDeRotasVO(int totalPercorrido ) {
		this.totalPercorrido = totalPercorrido;
	}

	public int getTotalPercorrido() {
		return totalPercorrido;
	}
	
	public void setTotalPercorrido(int totalPercorrido) {
		this.totalPercorrido = totalPercorrido;
	}
	
	public List<PontoVO> getLugaresPercorridos() {
		List<PontoVO> pontos = new ArrayList<PontoVO>();
		if(this.lugaresPercorridos != null){
			pontos = this.lugaresPercorridos;
		}
		return pontos;
	}
	
	public void setLugaresPercorridos(List<PontoVO> lugaresPercorridos) {
		this.lugaresPercorridos = lugaresPercorridos;
	}
	
	public void adicionarDistancia(int valor)
	{
		this.totalPercorrido = this.totalPercorrido + valor;
	}
	
	public void adicionarLugar(String lugar){
		PontoVO ponto = new PontoVO(lugar);
		adicionarLugar(getLugaresPercorridos(), ponto);
	}

	private void adicionarLugar(List<PontoVO> pontos, PontoVO ponto) {
		if(!pontos.contains(ponto)){
			pontos.add(ponto);	
		}
		setLugaresPercorridos(pontos);
	}

	public void adicionarLugares(List<PontoVO> lugares) {
		if(lugares != null){
			for (PontoVO ponto : lugares) {
				adicionarLugar(getLugaresPercorridos(), ponto);
			}	
		}
	}
	
	
}
