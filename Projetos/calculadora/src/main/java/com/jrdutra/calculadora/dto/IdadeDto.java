package com.jrdutra.calculadora.dto;

public class IdadeDto {

	private Integer idade;

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "IdadeDto [idade=" + idade + "]";
	}
	
}
