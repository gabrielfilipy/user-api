package com.br.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoExisteException extends RegraDeNegocioException{

	private static final long serialVersionUID = 1L;

	public EntidadeNaoExisteException(String mensagem) {
		super(mensagem);
	}
	
}
