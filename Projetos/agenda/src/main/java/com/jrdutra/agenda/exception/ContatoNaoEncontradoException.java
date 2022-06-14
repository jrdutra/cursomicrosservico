package com.jrdutra.agenda.exception;

public class ContatoNaoEncontradoException extends Exception {

	private static final long serialVersionUID = -8894881429961629591L;

	public ContatoNaoEncontradoException(String message) {
		super(formataMensagem(message));
	}
	
	public static String formataMensagem(String mensagem) {
		return "ContatoNaoEncontradoException: " + mensagem;
	}
	
}
