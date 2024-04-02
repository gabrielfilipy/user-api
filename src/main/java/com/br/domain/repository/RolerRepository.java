package com.br.domain.repository;

import com.br.domain.enums.RoleType;
import com.br.domain.model.Role;
import com.br.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolerRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleType(RoleType name);

}
