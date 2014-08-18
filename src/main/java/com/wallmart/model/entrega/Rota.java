package com.wallmart.model.entrega;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Entity;


@Entity
@Table(name = "ROTA")
@SequenceGenerator(sequenceName = "SEQ_ROTA" , name = "SEQ_ROTA",allocationSize=1,initialValue=1)
public class Rota implements Serializable{//,Comparable<Rota> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3568333045541297439L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ROTA")
	private Long id;

	private String origem;
	
	private String destino;

	//FIXME BIGDECIMAL
	private Integer distancia;
	
	public Rota() {
		super();
	}

	public Rota(String origem, String destino, Integer distancia) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

//	@Override
//	public int compareTo(Rota rota) {
//		if(this.getDistancia().intValue() > rota.getDistancia().intValue()){
//			return 1;
//		}else if(this.getDistancia().intValue() < rota.getDistancia().intValue()){
//			return -1;
//		}
//		return 0;
//	}
}
