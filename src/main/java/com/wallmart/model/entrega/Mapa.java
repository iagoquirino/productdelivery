package com.wallmart.model.entrega;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Entity;

@Entity
@Table(name = "MAPA")
@SequenceGenerator(sequenceName = "SEQ_MAPA" , name = "SEQ_MAPA",allocationSize=1,initialValue=1)
public class Mapa implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4585833083839610504L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MAPA")
	private Long id;
	
	private String nome;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "MAPA_ID")
	private List<Rota> rotas;

	public Mapa() {
		super();
	}
	
	public Mapa(String nome) {
		this.nome = nome;
	}

	public Mapa(long id, String nome) {
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
	
	public List<Rota> getRotas() {
		return rotas;
	}
	
	public void setRotas(List<Rota> rotas) {
		this.rotas = rotas;
	}

	public void adicionarRota(String origem, String destino, int distancia) {
		List<Rota> rotas = new ArrayList<Rota>();
		if(this.getRotas() != null){
			rotas = this.getRotas();
		}
		rotas.add(new Rota(origem,destino,distancia));
		setRotas(rotas);
	}
	
}
