package com.br.infrastructure.externalservice.rest.notification.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CodeMessage {

	private String userName;

    private String destinatario;
	
	private String codeValidation;
}
