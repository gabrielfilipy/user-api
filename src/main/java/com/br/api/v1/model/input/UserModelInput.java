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
	private Long departmentId;
	
}
