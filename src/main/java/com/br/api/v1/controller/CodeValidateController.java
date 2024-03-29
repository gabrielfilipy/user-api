package com.br.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.br.api.v1.mapper.CodeValidateModelMapper;
import com.br.api.v1.model.*;
import com.br.api.v1.model.input.CodeValidateEmailModelInput;
import com.br.domain.model.CodeValidate;
import com.br.domain.service.CodeValidateService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;



@Api(tags = "CodeValidate")
@RestController
@RequestMapping("/v1/codeValidate")
public class CodeValidateController {
	
	@Autowired
	private CodeValidateService codeValidateService;
	
	@Autowired
	private CodeValidateModelMapper codeValidateModelMapper;
	
	@PostMapping("/generated-code-validate")
	public ResponseEntity<CodeValidateEmailModel> gerarCode(@RequestBody  CodeValidateEmailModelInput codeValidateEmailModelInput) {
		Boolean isSave =  codeValidateService.save(codeValidateEmailModelInput.getEmail());
		CodeValidateEmailModel codeValidateEmailNew =  new CodeValidateEmailModel();
		codeValidateEmailNew.setValido(isSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(codeValidateEmailNew);
	}
//	@GetMapping("/user-validate/{code}")
//	public ResponseEntity<CodeValidateModel> codeValidate(@PathVariable(name = "code") String code) {
//		CodeValidate codeValidate  ;
//		CodeValidateModel codeValidateModel = 
//				codeValidateModelMapper.toModel(codeValidate);
//		return ResponseEntity.status(HttpStatus.OK).body(codeValidateModel);
//	}
}
