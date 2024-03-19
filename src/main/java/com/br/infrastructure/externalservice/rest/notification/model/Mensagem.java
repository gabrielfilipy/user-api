package com.br.infrastructure.externalservice.rest.notification.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Mensagem {

    private String destinatario;
	private String userLogin;
	private String userPassword;
	private String userName;

}
