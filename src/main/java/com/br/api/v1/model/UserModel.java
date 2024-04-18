package com.br.api.v1.model;

import lombok.*;

@Getter
@Setter
public class UserModel {

	private String nome;
	private Boolean active;
    private Long id;
	private String matricula;
	private String email;
	private Long cpf;
	private Long rg;
	private String endereco;
	private Long nascimento;
	private Long telefone;
	private Long departmentId;
	
	
}
