package com.br.api.v1.model.input;

import java.util.UUID;

import com.br.domain.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeValidateModelInput {
	
	private UUID id;

	private String code;
	
	private User user;
}
