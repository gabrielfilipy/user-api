package com.br.api.v1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.api.v1.mapper.CodeValidateModelMapper;
import com.br.api.v1.mapper.CodeValidateModelMapperBack;
import com.br.api.v1.model.CodeValidateEmailModel;
import com.br.api.v1.model.input.CodeValidateEmailModelInput;
import com.br.domain.service.CodeValidateService;

import io.swagger.annotations.Api;

@Api(tags = "CodeValidate")
@RestController
@RequestMapping("/v1/codeValidate")
public class CodeValidateController {
	
	@Autowired
	private CodeValidateService codeValidateService;

	
	@PostMapping("/generated-code-validate")
	public ResponseEntity<CodeValidateEmailModel> gerarCode(@RequestBody  CodeValidateEmailModelInput codeValidateEmailModelInput) {
		Boolean isSave =  codeValidateService.save(codeValidateEmailModelInput.getEmail());
		CodeValidateEmailModel codeValidateEmailNew =  new CodeValidateEmailModel();
		codeValidateEmailNew.setValido(isSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(codeValidateEmailNew);
	}

}
