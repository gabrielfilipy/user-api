package com.br.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.br.domain.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface UserService {

	User save(User user);
	Page<User> findAll(Specification<User> spec, Pageable pageable);
	User findById(Long id);
	User findByMatricula(String matricula);
	User findByEmail(String email);
	User deactivateUser(Long id);
	User activaUser(Long id, Boolean active);
	Page<User> Filtro(String matricula, String nome ,Long departmentId, Pageable pageable);
	List<User> buscarUsuariosDoDepartamento(Long departmentId);

}
