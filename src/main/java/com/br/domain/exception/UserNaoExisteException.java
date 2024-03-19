package com.br.domain.exception;

public class UserNaoExisteException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public UserNaoExisteException(String mensagem) {
		super(mensagem);
	}

}
