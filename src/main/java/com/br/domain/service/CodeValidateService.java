package com.br.domain.service;


import com.br.domain.model.CodeValidate;
public interface CodeValidateService {

	Boolean save(String matricula);	 
	CodeValidate findByCode(String code);
}
