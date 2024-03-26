package com.br.api.v1.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.api.v1.model.CodeValidateModel;
import com.br.domain.model.CodeValidate;

@Component
public class CodeValidateModelMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CodeValidateModel toModel(CodeValidate codeValidate) {
		CodeValidateModel codeValidateModelnew = 
				modelMapper.map(codeValidate, CodeValidateModel.class);
		return codeValidateModelnew;
	}
}