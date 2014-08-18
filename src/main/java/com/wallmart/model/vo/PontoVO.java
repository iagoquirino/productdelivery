package com.wallmart.model.vo;

public class PontoVO {
	
	private String nome;
	
	public PontoVO() {
		super();
	}
	
	public PontoVO(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PontoVO other = (PontoVO) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	
}
