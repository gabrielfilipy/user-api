package com.br.infrastructure.externalservice.rest.notification;

import javax.management.Notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.br.infrastructure.externalservice.rest.notification.model.CodeMessage;
import com.br.infrastructure.externalservice.rest.notification.model.Mensagem;

@Component
@FeignClient(value = "notification", url = "localhost:8083/v1/notification")
public interface NotificationFeignClient {

	@RequestMapping(method = RequestMethod.POST, value = "/registry-user")
	void registryUser(@RequestBody Mensagem mensagem);
	
	@RequestMapping(method = RequestMethod.POST, value = "/code-validation")
	Notification codeValidation(@RequestBody CodeMessage codeMessage);
	
}
