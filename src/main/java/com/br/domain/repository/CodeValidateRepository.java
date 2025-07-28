package com.br.domain.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.domain.model.CodeValidate;


@Repository
public interface CodeValidateRepository extends JpaRepository<CodeValidate, UUID> {
	CodeValidate findByCode(String code);
}

