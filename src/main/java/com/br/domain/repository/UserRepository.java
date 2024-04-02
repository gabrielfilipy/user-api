package com.br.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Value("FROM User WHERE matricula = :matricula")
	Optional<User> findByMatriculaSearch(String matricula);
	Optional<User> findByEmail(String email);

	@EntityGraph(attributePaths = "roles", type = EntityGraph.EntityGraphType.FETCH)
	Optional<User> findById(Long userId);

	@EntityGraph(attributePaths = "roles", type = EntityGraph.EntityGraphType.FETCH)
	Optional<User> findByNome(String nome);

}
