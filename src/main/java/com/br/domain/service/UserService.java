package com.br.domain.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.domain.model.User;

public interface UserService {

	User save(User user);
	Page<User> findAll(Pageable pageable);
	User findById(Long id);
	User findByMatricula(String matricula);
	User findByEmail(String email);
	User deactivateUser(Long id);
	User activaUser(Long id, Boolean active);
}
