package com.br.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import com.br.domain.model.User;
import com.br.domain.repository.UserRepositoryQuery;


public class UserRepositoryImpl implements UserRepositoryQuery{
	
	 @PersistenceContext
	    private EntityManager manager;

	    @Override
		public Page<User> Filtro(String matricula, String nome, UUID departmentId, Pageable pageable) {
	        CriteriaBuilder builder = manager.getCriteriaBuilder();
	        CriteriaQuery<User> criteria = builder.createQuery(User.class);
	        Root<User> root = criteria.from(User.class);

	        Predicate[] predicates = criarRestricoes(matricula, nome, departmentId, builder, root);
	        criteria.where(predicates);
	        criteria.select(root).distinct(true);
	        TypedQuery<User> query = manager.createQuery(criteria);
	        adicionarRestricoesDePaginacao(query, pageable);
	        return new PageImpl<>(query.getResultList(), pageable, totalElementos(matricula, nome, departmentId));
	    }


	    private Predicate[] criarRestricoes(String matricula, String nome, UUID departmentId, CriteriaBuilder builder, Root<User> root) {
	        List<Predicate> predicates = new ArrayList<>();
	        
	  
	        if (StringUtils.hasLength(matricula)) {
	            predicates.add(builder.equal(root.get("matricula"), matricula));
	        }
	        
	        if (departmentId != null) {
	            predicates.add(builder.equal(root.get("departmentId"), departmentId));
	        }

	        return predicates.toArray(new Predicate[0]);
	    }

	    private void adicionarRestricoesDePaginacao(TypedQuery<User> query, Pageable pageable) {
	        int paginaAtual = pageable.getPageNumber();
	        int totalRegistrosPorPagina = pageable.getPageSize();
	        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
	        query.setFirstResult(primeiroRegistroDaPagina);
	        query.setMaxResults(totalRegistrosPorPagina);
	    }


	    private Long totalElementos(String matricula, String nome, UUID departmentId) {
	        CriteriaBuilder builder = manager.getCriteriaBuilder();
	        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
	        Root<User> root = criteria.from(User.class);
	        Predicate[] predicates = criarRestricoes(matricula, nome, departmentId, builder, root);
	        criteria.where(predicates);
	        criteria.select(builder.count(root));
	        return manager.createQuery(criteria).getSingleResult();
	    }
}
