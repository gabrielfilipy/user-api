package com.br.api.v1.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMatriculaModel {

	private String matricula;
	private String nome;
	private UUID userId;
	
}
