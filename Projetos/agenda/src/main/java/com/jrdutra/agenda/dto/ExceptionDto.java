package com.jrdutra.agenda.dto;

import org.springframework.http.HttpStatus;

public class ExceptionDto {
	
	private HttpStatus status;
	
	private String mensagem;
	
	public ExceptionDto(HttpStatus status, String mensagem) {
		super();
		this.status = status;
		this.mensagem = mensagem;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return "ExceptionDto [status=" + status + ", mensagem=" + mensagem + "]";
	}
}
