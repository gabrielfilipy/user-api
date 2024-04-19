package com.br.api.v1.model.input;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UserActiveModelInput {

	private boolean active;
	
}
