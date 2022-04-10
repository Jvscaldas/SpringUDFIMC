package br.com.joaosantana.SpringUDF.model;

public class Aluno {

	private int cod;
	private String nome;
	private String condicao;
	private float altura;
	private float peso;
	private float imc;

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getImc() {
		return imc;
	}

	public void setImc(float imc) {
		this.imc = imc;
	}

	@Override
	public String toString() {
		return "Aluno [cod=" + cod + ", nome=" + nome + ", condicao=" + condicao + ", altura=" + altura + ", peso="
				+ peso + ", imc=" + imc + "]";
	}

}
