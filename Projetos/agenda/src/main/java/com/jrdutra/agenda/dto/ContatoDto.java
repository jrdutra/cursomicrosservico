package com.jrdutra.agenda.dto;

import java.util.Date;

public class ContatoDto {

private long id;
	
	private String nome;
	
	private String telefone;
	
	private String email;
	
	private Date dataNascimento;
	
	private Integer idade;
	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	
	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "ContatoDto [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email
				+ ", dataNascimento=" + dataNascimento + ", idade=" + idade + "]";
	}
}
