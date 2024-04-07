package com.br.domain.service;

import com.br.domain.enums.RoleType;
import com.br.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> findByRoleName(RoleType name);
    List<Role> findAll();

}
