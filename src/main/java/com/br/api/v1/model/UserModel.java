package com.br.api.v1.model;

import lombok.*;

@Getter
@Setter
public class UserModel {

private String nome;
	
    private Long id;
	private String password;
	private String matricula;
	private String email;
	
}
