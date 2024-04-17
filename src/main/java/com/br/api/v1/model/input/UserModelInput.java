package com.br.api.v1.model.input;

import lombok.*;

@Getter
@Setter
public class UserModelInput {

	private Long id;
	private String nome;
	private String password;
	private String matricula;
	private String email;
	private Long cpf;
	private Long rg;
	private String endereco;
	private Long nascimento;
	private Long telefone;
	private Long departmentId;
	
}
