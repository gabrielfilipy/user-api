package com.br.api.v1.model.input;

import com.br.domain.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeValidateModelInput {
	
	private Long id;

	private String code;
	
	private User user;
}
