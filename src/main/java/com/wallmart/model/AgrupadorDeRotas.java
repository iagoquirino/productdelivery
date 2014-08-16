package com.wallmart.model;

import java.util.ArrayList;
import java.util.List;

public class AgrupadorDeRotas {
	
	private int totalPercorrido;
	
	private List<Ponto> lugaresPercorridos;

	public AgrupadorDeRotas() {
		super();
	}
	
	public AgrupadorDeRotas(int totalPercorrido ) {
		this.totalPercorrido = totalPercorrido;
	}

	public int getTotalPercorrido() {
		return totalPercorrido;
	}
	
	public void setTotalPercorrido(int totalPercorrido) {
		this.totalPercorrido = totalPercorrido;
	}
	
	public List<Ponto> getLugaresPercorridos() {
		List<Ponto> pontos = new ArrayList<Ponto>();
		if(this.lugaresPercorridos != null){
			pontos = this.lugaresPercorridos;
		}
		return pontos;
	}
	
	public void setLugaresPercorridos(List<Ponto> lugaresPercorridos) {
		this.lugaresPercorridos = lugaresPercorridos;
	}
	
	public void adicionarDistancia(int valor)
	{
		this.totalPercorrido = this.totalPercorrido + valor;
	}
	
	public void adicionarLugar(String lugar){
		Ponto ponto = new Ponto(lugar);
		adicionarLugar(getLugaresPercorridos(), ponto);
	}

	private void adicionarLugar(List<Ponto> pontos, Ponto ponto) {
		if(!pontos.contains(ponto)){
			pontos.add(ponto);	
		}
		setLugaresPercorridos(pontos);
	}

	public void adicionarLugares(List<Ponto> lugares) {
		if(lugares != null){
			for (Ponto ponto : lugares) {
				adicionarLugar(getLugaresPercorridos(), ponto);
			}	
		}
	}
	
	
}
