package com.br.api.v1.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
public class UserModel {

	private String nome;
	private Boolean active;
    private UUID userId;
	private String matricula;
	private String email;
	private UUID departmentId;
	private String telefone;
	private String endereco;
	private String rg;
	private String cpf;
	
}
