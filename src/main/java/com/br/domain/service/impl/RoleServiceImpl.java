package com.br.domain.service.impl;

import com.br.domain.enums.RoleType;
import com.br.domain.model.Role;
import com.br.domain.repository.RolerRepository;
import com.br.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RolerRepository rolerRepository;

    @Override
    public Optional<Role> findByRoleName(RoleType name) {
        Role roleNew = new Role();
        Optional<Role> role = rolerRepository.findByRoleType(name);
        //TODO: Melhorar essa implementação fazendo com que ele insira uma nova ROLE caso ela não exista.
        if(role.isEmpty()) {
            roleNew.setRoleType(RoleType.ROLE_FUNCIONARIO);
            rolerRepository.save(roleNew);
        }
        return rolerRepository.findByRoleType(name);
    }

	@Override
	public List<Role> findAll() {
		return rolerRepository.findAll();
	}

}
