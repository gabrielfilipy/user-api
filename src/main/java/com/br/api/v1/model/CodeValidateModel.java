package com.br.api.v1.model;

import java.util.UUID;

import com.br.domain.model.User;

import lombok.*;

@Getter
@Setter
public class CodeValidateModel {

	private UUID id;

	private String code;
	
	private User user;
}
