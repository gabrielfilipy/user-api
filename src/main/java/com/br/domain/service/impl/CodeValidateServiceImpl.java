package com.br.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.core.config.Generator;
import com.br.domain.model.CodeValidate;
import com.br.domain.model.User;
import com.br.domain.repository.CodeValidateRepository;
import com.br.domain.service.CodeValidateService;
import com.br.domain.service.UserService;
import com.br.infrastructure.externalservice.rest.notification.NotificationFeignClient;

@Service
public class CodeValidateServiceImpl implements CodeValidateService {
	
	private static final int TAMANHO_CODE = 6;

	@Autowired
	private CodeValidateRepository codeValidateRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NotificationFeignClient notificationFeignClient;
	
	@Autowired
	private Generator generator;
	
	@Override
	public Boolean save(String matricula) {
		User user = userService.findByMatricula(matricula);
		CodeValidate codeValidate = new CodeValidate();
		codeValidate.setUser(user);
		codeValidate.setCode(generator.password(TAMANHO_CODE));
		CodeValidate codeValidateSave = codeValidateRepository.save(codeValidate);
		
		//Todo: Notificar o usuário com o código de validação.
		
		return true;
	}

}
