package com.br.infrastructure.externalservice.rest.department.model;

import java.util.UUID;

import lombok.*;

@Getter
@Setter
public class Department {

	private UUID departamentoId;
	private UUID orgaoId;
	private String nome;
	private String sigla;
	private String unidadePai;
	private String localidade;
	
}
