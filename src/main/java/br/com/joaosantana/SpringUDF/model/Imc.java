package br.com.joaosantana.SpringUDF.model;

public class Imc {

	private int id;
	private String faixa;
	private String condicao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFaixa() {
		return faixa;
	}

	public void setFaixa(String faixa) {
		this.faixa = faixa;
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	@Override
	public String toString() {
		return "Imc [id=" + id + ", faixa=" + faixa + ", condicao=" + condicao + "]";
	}

}
