package com.br.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.User;
import com.br.domain.repository.UserRepository;
import com.br.domain.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserRepository userRepository;
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new EntidadeNaoExisteException("Usuário informado não existe: " + id);
		}
		return user.get();
	}

	@Override
	public Optional<User> findByMatricula(String matricula) {
		return userRepository.findByMatricula(matricula);
	}

}
