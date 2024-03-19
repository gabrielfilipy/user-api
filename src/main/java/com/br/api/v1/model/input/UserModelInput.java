package com.br.api.v1.model.input;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
public class UserModelInput {

<<<<<<< HEAD
	private String nome;
=======
    private String nome;
>>>>>>> 1f5c5d5fb8a7b1d836144fe2e4f2cb7cd68c7d70
	private String password;
	private String matricula;
	private String email;
	private Long departmentId;
	
}
