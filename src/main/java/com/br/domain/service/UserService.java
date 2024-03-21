package com.br.domain.service;

import java.util.List;
import com.br.domain.model.User;

public interface UserService {

	User save(User user);
	List<User> findAll();
	User findById(Long id);
	User findByMatricula(String matricula);
	User findByEmail(String email);
	User deactivateUser(Long id);
	User activateUser(Long id);
}
