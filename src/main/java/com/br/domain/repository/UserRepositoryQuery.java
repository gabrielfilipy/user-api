package com.br.domain.repository;

import java.util.UUID;

import org.springframework.data.domain.*;
import com.br.domain.model.User;

public interface UserRepositoryQuery {
	
	Page<User> Filtro(String matricula, String nome ,UUID departmentId, Pageable pageable);


}
