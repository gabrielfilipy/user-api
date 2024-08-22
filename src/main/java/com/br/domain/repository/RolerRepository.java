package com.br.domain.repository;

import com.br.domain.enums.RoleType;
import com.br.domain.model.Role;
import com.br.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RolerRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByRoleType(RoleType name);

}
