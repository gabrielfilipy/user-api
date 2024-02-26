package com.br.domain.service;

import java.util.List;
import java.util.Optional;

import com.br.domain.model.User;

public interface UserService {

	User save(User user);
	List<User> findAll();
	User findById(Long id);
	Optional<User> findByMatricula(String matricula);
	
}
