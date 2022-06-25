package com.jrdutra.agenda.dto;

public class ContatoDto {

	private long id;
	
	private String nome;
	
	private String telefone;
	
	private String email;
	
	private Integer idade;

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ContatoDto [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", idade="
				+ idade + "]";
	}
}
