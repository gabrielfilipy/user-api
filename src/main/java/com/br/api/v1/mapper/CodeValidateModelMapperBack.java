package com.br.api.v1.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.api.v1.model.input.CodeValidateModelInput;
import com.br.domain.model.CodeValidate;

@Component
public class CodeValidateModelMapperBack {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CodeValidate toModel(CodeValidateModelInput codeValidateModelInput) {
		CodeValidate codeValidate =
				modelMapper.map(codeValidateModelInput, CodeValidate.class);
		return codeValidate;
	}

}
