package com.br.api.v1.model;

import com.br.domain.model.User;

import lombok.*;

@Getter
@Setter
public class CodeValidateModel {

	private Long id;

	private String code;
	
	private User user;
}
