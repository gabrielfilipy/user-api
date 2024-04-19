package com.br.api.v1.model;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
public class UserModel {

	private String nome;
	private Boolean active;
    private Long id;
	private String matricula;
	private String email;
	private Long departmentId;
	private String telefone;
	private String endereco;
	private String rg;
	private String cpf;
	
}
