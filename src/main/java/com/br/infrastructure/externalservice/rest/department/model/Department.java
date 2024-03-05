package com.br.infrastructure.externalservice.rest.department.model;

import lombok.*;

@Getter
@Setter
public class Department {

	private Long id;
	private String orgao;
	private String nome;
	private String sigla;
	private String unidadePai;
	private String localidade;
	
}
