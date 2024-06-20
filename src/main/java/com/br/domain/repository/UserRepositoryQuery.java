package com.br.domain.repository;

import org.springframework.data.domain.*;
import com.br.domain.model.User;

public interface UserRepositoryQuery {
	
	Page<User> Filtro(String matricula, String nome ,Long departmentId, Pageable pageable);


}
