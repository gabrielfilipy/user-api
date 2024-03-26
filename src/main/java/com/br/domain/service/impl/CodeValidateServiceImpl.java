package com.br.domain.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.core.config.Generator;
import com.br.domain.model.*;
import com.br.domain.repository.CodeValidateRepository;
import com.br.domain.service.*;
import com.br.infrastructure.externalservice.rest.notification.NotificationFeignClient;
import com.br.infrastructure.externalservice.rest.notification.model.CodeMessage;


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
	public Boolean save(String email) {
		User user = userService.findByEmail(email);
		CodeValidate codeValidate = new CodeValidate();
		codeValidate.setUser(user);
		codeValidate.setCode(generator.password(TAMANHO_CODE));
		CodeValidate codeValidateSave = codeValidateRepository.save(codeValidate);
		
		//Todo: Notificar o usuário com o código de validação.
		
		notificationFeignClient.codeValidation(new CodeMessage(
				user.getNome(),
				user.getEmail(), 
				codeValidate.getCode())
				);
		
		return true;
	  }

	@Override
	public CodeValidate findByCode(String code) {
		CodeValidate codeValidate = codeValidateRepository.findByCode(code);
        if (codeValidate != null) {
            User user = codeValidate.getUser();
            if (user != null) {
            	String matricula = user.getMatricula();                
                user = userService.findByMatricula(matricula);
                
                codeValidate.setCode(code);
                codeValidate.setUser(user);

                return codeValidate;
            }
        }
        return null;
    }
}
	 