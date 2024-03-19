package com.br.api.v1.model.input;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
public class UserModelInput {

	private String nome;
	private String password;
	private String matricula;
	private String email;
	private Long departmentId;
	
}
