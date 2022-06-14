package com.jrdutra.agenda.exception;

public class ConexaoCalculadoraException extends Exception{

	private static final long serialVersionUID = -3169036128952905030L;
	
	public ConexaoCalculadoraException(String message) {
		super(formataMensagem(message));
	}

	public static String formataMensagem(String mensagem) {
		return "ConexaoCalculadoraException: " + mensagem;
	}
}
