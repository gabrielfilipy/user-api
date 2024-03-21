package com.br.api.v1.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.api.v1.model.CodeValidateModel;

@Component
public class CodeValidateModelMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CodeValidateModel toModel(CodeValidateModel codeValidateModel) {
		CodeValidateModel codeValidateModelnew = 
				modelMapper.map(codeValidateModel, CodeValidateModel.class);
		return codeValidateModelnew;
	}
}