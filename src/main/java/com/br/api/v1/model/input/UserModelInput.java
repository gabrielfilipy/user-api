package com.br.api.v1.model.input;

import java.util.UUID;

import lombok.*;

@Getter
@Setter
public class UserModelInput {

	private UUID userId;
	private String nome;
	private String password;
	private String matricula;
	private String email;
	private UUID departmentId;
	private String telefone;
	private String endereco;
	private String rg;
	private String cpf;

}
