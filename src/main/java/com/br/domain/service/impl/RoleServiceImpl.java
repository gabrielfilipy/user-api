package com.br.domain.service.impl;

import com.br.domain.enums.RoleType;
import com.br.domain.model.Role;
import com.br.domain.repository.RolerRepository;
import com.br.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RolerRepository rolerRepository;

    @Override
    public Optional<Role> findByRoleName(RoleType name) {
        return rolerRepository.findByRoleType(name);
    }

}
