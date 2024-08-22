package com.br.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.br.domain.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface UserService {

	User save(User user);
	Page<User> findAll(Specification<User> spec, Pageable pageable);
	User findById(UUID id);
	User findByMatricula(String matricula);
	User findByEmail(String email);
	User deactivateUser(UUID id);
	User activaUser(UUID id, Boolean active);
	Page<User> Filtro(String matricula, String nome ,UUID departmentId, Pageable pageable);
	List<User> buscarUsuariosDoDepartamento(UUID departmentId);
	void processForgotPassword(String email);
}
