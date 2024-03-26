package com.br.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.domain.model.CodeValidate;


@Repository
public interface CodeValidateRepository extends JpaRepository<CodeValidate, Long> {
	CodeValidate findByCode(String code);
}

