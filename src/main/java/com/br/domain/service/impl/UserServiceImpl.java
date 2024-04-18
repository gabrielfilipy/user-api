package com.br.domain.service.impl;

import java.util.List;
import java.util.Optional;

import com.br.domain.enums.RoleType;
import com.br.domain.model.Role;
import com.br.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.core.config.Generator;
import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.User;
import com.br.domain.repository.UserRepository;
import com.br.domain.service.UserService;
import com.br.infrastructure.externalservice.rest.department.DepartmentFeignClient;
import com.br.infrastructure.externalservice.rest.department.model.Department;
import com.br.infrastructure.externalservice.rest.notification.NotificationFeignClient;
import com.br.infrastructure.externalservice.rest.notification.model.Mensagem;

@Service
public class UserServiceImpl implements UserService {
	
	private static final int TAMANHO_SENHA = 8;

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private DepartmentFeignClient departmentFeignClient;
	
	@Autowired
	private NotificationFeignClient notificationFeignClient;
	
	@Autowired
	private Generator generator;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;
	
	@Override
	public User save(User user) {
		Department department = departmentFeignClient.getDepartment(user.getDepartmentId());
		User userSave = userRepository.save(user);
		String matricula = getMatricula(department, userSave.getId());
		String password = generator.password(TAMANHO_SENHA);
		Role role = roleService.findByRoleName(RoleType.ROLE_FUNCIONARIO)
				.orElseThrow(() -> new RuntimeException("Error: Permissão 'ROLE_FUNCIONARIO´  não existe."));
		String passwordEncode = passwordEncoder.encode(password);
		System.out.println(">>>>>>>>>>>>>>>>> SENHA: " + password);
		System.out.println(">>>>>>>>>>>>>>>>> MATRICULA: " + matricula);
		userSave.setMatricula(matricula);
		userSave.setPassword(passwordEncode);
		userSave.getRoles().add(role);
		userSave = userRepository.save(userSave);
		notificationFeignClient.registryUser(new Mensagem(
				user.getEmail(), 
				matricula, 
				password, 
				user.getNome())
				);
		
		return userSave;
	}

	private String getMatricula(Department department, Long id) {
        generator.sigla(id);
		return generator.getSigla(department.getSigla());
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
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
	public User findByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if(user.isEmpty()) {
			throw new EntidadeNaoExisteException("E-mail informado nao existe: " + email);
		}
		return user.get();
	}
	
	@Override
	public User findByMatricula(String matricula) {
		Optional <User> user = userRepository.findByMatriculaSearch(matricula);
		if(user.isEmpty()) {
			throw new EntidadeNaoExisteException("Matricula não existe: " + matricula);
		}
		return user.get();
	}

	@Override
    public User deactivateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.setActive(false);
        return userRepository.save(user);
	}

	@Override
	public User activateUser(Long id) {
        User user= userRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoExisteException("Usuário não encontrado"));
        user.setActive(true);
        return userRepository.save(user);
    }

}
